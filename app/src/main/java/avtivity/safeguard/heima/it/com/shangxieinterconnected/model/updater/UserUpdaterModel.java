package avtivity.safeguard.heima.it.com.shangxieinterconnected.model.updater;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.NotificationCompat;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import avtivity.safeguard.heima.it.com.shangxieinterconnected.R;
import avtivity.safeguard.heima.it.com.shangxieinterconnected.model.IshangXieInterconnectedUri;
import avtivity.safeguard.heima.it.com.shangxieinterconnected.model.updater.IUpdater.IUpdaterModel;
import avtivity.safeguard.heima.it.com.shangxieinterconnected.model.updater.IUpdater.OnUpdaterModel;
import avtivity.safeguard.heima.it.com.shangxieinterconnected.model.utils.Constants;
import avtivity.safeguard.heima.it.com.shangxieinterconnected.model.utils.SharedPerfsUtils;
import avtivity.safeguard.heima.it.com.shangxieinterconnected.view_activity.MainActivity;

/*
 *  @项目名：  ShangXieInterconnected 
 *  @包名：    avtivity.safeguard.heima.it.com.shangxieinterconnected.model.updater
 *  @文件名:   UserUpdaterModel
 *  @创建者:   文
 *  @创建时间:  2017/3/9 12:03
 *  @描述：    获取更新数据的方法
 */
public class UserUpdaterModel
        implements IUpdaterModel
{

    private static final String TAG         = "UserUpdaterModel";
    private              int    threadCount = 3;    //开启三条线程下载
    private              int    create      = 0;    //记录下载所有的总数的
    private String                     mUpdater;       //记录访问网络地址
    private int                        mContentLength;   //记录网上文件的到大小
    private NotificationManager        mNm;
    private NotificationCompat.Builder mBuilder;          //通知栏显示对话框
    private Notification               mNf;            //通知栏显示对话框
    File mFiles;                                       //文件路径
    private File mFilePath;

    /**
     * 加载网络获取版本信息,并判断是否是最新的版本
     * @param listener
     */
    @Override
    public void updater(OnUpdaterModel listener) {
        InputStream    is = null;
        BufferedReader br = null;
        //获取到本地的版本
        String UpdaterUrl = IshangXieInterconnectedUri.HOME + IshangXieInterconnectedUri.UPDATER_URI;
        try {
            URL               url        = new URL(UpdaterUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.connect();
            StringBuffer mUpdaterData = new StringBuffer();
            if (connection.getResponseCode() == 200) {
                is = connection.getInputStream();
                br = new BufferedReader(new InputStreamReader(is));
                String buffer;
                while ((buffer = br.readLine()) != null) {
                    mUpdaterData.append(buffer);
                }
                String       data       = mUpdaterData.toString();
                JSONObject   json       = new JSONObject(data);
                StringBuffer serverDate = new StringBuffer();
                serverDate.append(json.getString("data"));
                String version  = serverDate.toString();
                int    len      = version.indexOf("appVersionNo");
                int    urlStart = serverDate.indexOf("url");
                int    urlEnd   = serverDate.indexOf("versionType");
                String num      = version.substring(len + 14, urlStart - 2);

                mUpdater = version.substring(urlStart + 6, urlEnd - 2)
                                  .replace("\\", "");
                listener.updater(Integer.valueOf(num), mUpdater);
            } else {
                listener.error();
            }
        } catch (MalformedURLException e) {
            listener.error();
            e.printStackTrace();
        } catch (IOException e) {
            listener.error();
            e.printStackTrace();
        } catch (JSONException e) {
            listener.error();
            e.printStackTrace();

        } finally {
            closeStream(is);
            closeStream(br);

        }

    }

    /**
     *  下载更新的版本
     * @param updaterUrl
     * @param pd
     * @param upldaterPresenter
     */
    @Override
    public void updateDownload(String updaterUrl,
                               ProgressDialog pd,
                               Context context,
                               File fiels,
                               OnUpdaterModel upldaterPresenter)
    {
        try {
            URL               url        = new URL(updaterUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);     //设置超时时间
            connection.setReadTimeout(5000);        //设置读取超时时间
            connection.connect();                   //表示准备好了

            if (connection.getResponseCode() == 200) {

                //获取服务器上文件的大小
                mContentLength = connection.getContentLength();

                mFiles = getFile(fiels);
                mFilePath = new File(mFiles, getFileName(updaterUrl));
                RandomAccessFile raf = new RandomAccessFile(mFilePath, "rw");
                raf.setLength(mContentLength);    //设置文件的大小
                pd.setMax(mContentLength);
                closeStream(raf);
                mNm = (NotificationManager) context.getSystemService(Activity.NOTIFICATION_SERVICE);
                mBuilder = new NotificationCompat.Builder(context);
                int blockSize = mContentLength / threadCount;      //平均每条线程下载多少数据
                for (int threadId = 0; threadId < threadCount; threadId++) {
                    int startIndex = threadId * blockSize;          //获取每个线程开始的位置
                    int endIndex   = (threadId + 1) * blockSize - 1;       //获取每个文件结束的位置

                    if (threadId == threadCount - 1) {     //如果是最后一线程,那就下载完后面剩余的内容
                        endIndex = mContentLength - 1;
                    }
                    new DownloadThread(threadId, startIndex, endIndex, context, pd).start();
                }
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
            upldaterPresenter.error();

        } catch (IOException e) {
            upldaterPresenter.error();
            e.printStackTrace();

        }
        while (true) {
            if (create >= mContentLength) {
                return;
            }
        }

    }

    /**
     * 多线程断点下载断点续传的线程
     */
    private class DownloadThread
            extends Thread
    {
        private final int            threadId;      //线程的id个数
        private final int            startIndex;    //开始下载的位置
        private final int            endIndex;      //结束下载的位置
        private final ProgressDialog pd;
        private final Context        context;
        private       int            currentPostion;       //实时的位置,存档是存取当前下载的位置
        private InputStream mIn = null;

        private int runingThreadCountCount = 3;

        private RandomAccessFile mRaf;

        public DownloadThread(int threadId,
                              int startIndex,
                              int endIndex,
                              Context context,
                              ProgressDialog pd)
        {
            this.threadId = threadId;
            this.startIndex = startIndex;
            this.currentPostion = startIndex;
            this.endIndex = endIndex;
            this.pd = pd;
            this.context = context;
        }

        @Override
        public void run() {
            try {
                URL               url  = new URL(mUpdater);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                mRaf = new RandomAccessFile(mFilePath, "rw");
                File files = new File(mFiles, threadId + ".postion");
                if (files.exists() && files.length() > 0) {    //文件存在
                    BufferedReader br      = new BufferedReader(new FileReader(files));
                    String         linVale = br.readLine();
                    currentPostion = Integer.valueOf(linVale);
                    conn.setRequestProperty("range",
                                            "bytes=" + currentPostion + "-" + endIndex);  //设置从哪里开始
                    mRaf.seek(currentPostion);   //设置从指定的位置开始
                    closeStream(br);
                } else {
                    conn.setRequestProperty("range", "bytes=" + startIndex + "-" + endIndex);
                    mRaf.seek(startIndex);
                }
                if (conn.getResponseCode() == 206) {
                    mIn = conn.getInputStream();
                    int    len;
                    byte[] buf = new byte[1024 * 1024];
                    while ((len = mIn.read(buf)) != -1) {
                        currentPostion += len;
                        synchronized (MainActivity.class) {
                            create += len;
                            pd.setProgress(create);

                            int mValues = ((int) (create * 100 / mContentLength)); //下载进度
                            mBuilder.setProgress(100, mValues, false);
                            mBuilder.setSmallIcon(R.drawable.ic_launcher);
                            mBuilder.setContentInfo("下载中: " + create + "//" + mContentLength);
                            mBuilder.setContentTitle("正在下载");
                            mNf = mBuilder.build();
                            mNm.notify(0, mNf);
                            if (mValues == 100) {
                                //Intent it = new Intent(Intent.ACTION_VIEW);
                                Intent it = new Intent(Intent.ACTION_MAIN);
                                //it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                                it.setDataAndType(Uri.parse("file:" + mFilePath.getAbsolutePath()),
                                                  "application/vnd.android.package-archive");
                                PendingIntent pendingIntent = PendingIntent.getActivity(context,
                                                                                        0,
                                                                                        it,
                                                                                        PendingIntent.FLAG_UPDATE_CURRENT);
                                mBuilder.setContentTitle("下载完成");
                                mBuilder.setContentText("点击安装");
                                mBuilder.setContentInfo("下载完成");
                                mBuilder.setContentIntent(pendingIntent);
                                mNf = mBuilder.build();
                                mNm.notify(0, mNf);
                                SharedPerfsUtils.putString(context,
                                                           Constants.DOWNLOAD_UPDATER_OK,
                                                           mFilePath.getAbsolutePath());
                                // mNm.cancel(0);
                                //发送广播

                                Intent intnt = new Intent();
                                intnt.setAction("updaterReceiver");
                                //intnt.putExtra("updater1", "111");
                                context.sendBroadcast(intnt);

                            }
                        }
                        mRaf.write(buf, 0, len);
                        RandomAccessFile ra = new RandomAccessFile(new File(mFiles,
                                                                            threadId + ".postion"),
                                                                   "rws");   //实时写到硬盘中比较耗费硬盘
                        ra.write((String.valueOf(currentPostion)).getBytes());
                        closeStream(ra);


                    }
                }
                files.renameTo(new File(mFiles, threadId + ".potition.finish"));
                synchronized (UserUpdaterModel.class) {

                    runingThreadCountCount--;
                    if (runingThreadCountCount <= 0) {
                        for (int i = 0; i < threadCount; i++) {
                            File fil = new File(mFiles, i + ".position.finish");
                            fil.delete();
                        }
                    }
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                closeStream(mIn);
                closeStream(mRaf);
            }
        }
    }


    /**
     * 返回apk名字
     * @param path
     * @return
     */
    private String getFileName(String path) {
        int    startIndexof = path.lastIndexOf("/");
        int    endIndexof   = path.lastIndexOf("?");
        String name         = path.substring(startIndexof + 1, endIndexof);
        return name;
    }

    /**
     * 关闭流,封装的方法
     * @param cloaeable
     */
    public void closeStream(Closeable cloaeable) {
        if (cloaeable != null) {
            try {
                cloaeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            cloaeable = null;
        }
    }

    /**
     * 将下载的路径传递给p层,p层再传递给m层
     * @return
     */
    @Override
    public File getFiles() {
        return mFiles;

    }

    /**
     * 获取存在的路径
     * @param card
     * @return
     */
    public File getFile(File card) {
        File file = Environment.getExternalStorageDirectory();
        if (!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            file = card;
        }

        return file;
    }

}

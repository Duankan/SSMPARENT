package com.ktw.duan.utils;

import com.ktw.duan.base.StreamEnum;
import org.apache.commons.lang3.StringUtils;
import java.io.*;
import java.util.*;

/**
 * 输入流工具类
 */
public class StreamUtils {
    private static InputStream inputStream;
    private static OutputStream outputStream;
    private static BufferedOutputStream bufferedOutputStream;
    private static BufferedInputStream bufferedInputStream;
    private static InputStreamReader inStream = null;
    private static OutputStreamWriter outStream = null;
    private static BufferedReader bufferedReader;
    private static BufferedWriter bufferedWriter;

    /**
     * 字节流复制文本,并根据文件路径和文类型随机地生成同盘符同类型的文件
     */
    public static byte[] CopyLocalFileByte(File originFile) {
        byte[] bytes = new byte[1024];
        int len = -1;
        String NewPath = CreateMidrs(originFile);
        File newFile = new File(NewPath);
        Map<String, Object> Streams = new TreeMap<>();
        //判断文件存在
        if (originFile.exists()) {
            try {
                inputStream = new FileInputStream(originFile);
                outputStream = new FileOutputStream(newFile);
                Streams.put("outputStream", outputStream);
                Streams.put("inputStream", inputStream);
                //每次读取1024个字节，并保存到字节数组
                while ((len = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, len);
                }
            } catch (Exception E) {
                E.printStackTrace();
            } finally {
                //关闭流的顺序？？
                CloseStream(Streams);
            }
        }
        return bytes;
    }

    /**
     * 缓冲字节流复制文本
     */
    public static byte[] CopyLocalFileByteBuffer(File originFile) {
        byte[] bytes = new byte[1024];
        int len = -1;
        String NewPath = CreateMidrs(originFile);
        File newFile = new File(NewPath);
        Map<String, Object> Streams = new TreeMap<>();
        try {
            inputStream = new FileInputStream(originFile);
            outputStream = new FileOutputStream(newFile);
            bufferedInputStream = new BufferedInputStream(inputStream);//传参io
            bufferedOutputStream = new BufferedOutputStream(outputStream);//传参io
            Streams.put("bufferedoutputStream", bufferedOutputStream);
            Streams.put("outputStream", outputStream);
            Streams.put("bufferedinputStream", bufferedInputStream);
            Streams.put("inputStream", inputStream);
            while ((len = bufferedInputStream.read(bytes)) != -1) {
                bufferedOutputStream.write(bytes, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseStream(Streams);
        }
        return bytes;
    }

    /**
     * 缓冲字符流(本地文件gbk的编码！！)
     */
    public static String GetContentByCharBuffer(File originFile) {
        char[] chars = new char[1024];
        int len = -1;
        String NewPath = CreateMidrs(originFile);
        File newFile = new File(NewPath);
        Map<String, Object> Streams = new TreeMap<>();
        StringBuffer stringBuffer=new StringBuffer();
        try {
            inStream = new InputStreamReader(new FileInputStream(originFile), "UTF-8");
            outStream = new OutputStreamWriter(new FileOutputStream(newFile), "UTF-8");
            bufferedReader=new BufferedReader(inStream);
            bufferedWriter=new BufferedWriter(outStream);
            Streams.put("bufferedWriter",bufferedWriter);
            Streams.put("writer",outStream);
            Streams.put("bufferedReader",bufferedReader);
            Streams.put("reader",inStream);
            while ((len=bufferedReader.read(chars))!=-1){
                stringBuffer.append(new String(chars,0,len));
                bufferedWriter.write(chars,0,len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            CloseStream(Streams);
        }
        return stringBuffer.toString();
    }

    /**
     * 创建文件夹并返回拼接的新文件全路径
     *
     * @param originFile
     * @return
     */
    public static String CreateMidrs(File originFile) {
        Calendar now = Calendar.getInstance();
        String year = String.valueOf(now.get(Calendar.YEAR));
        String month = String.valueOf(now.get(Calendar.MONTH) + 1);
        String day = String.valueOf(now.get(Calendar.DAY_OF_MONTH));
        String time = String.valueOf(now.getTimeInMillis());
        //创建外面层级文件夹
        String midrspath = StringUtils.substringBefore(originFile.getPath(), originFile.getName())
                + year + File.separator + month + File.separator + day;
        File midrs = new File(midrspath);
        midrs.mkdirs();
        String NewPath = StringUtils.substringBefore(originFile.getPath(), originFile.getName())
                + year + File.separator + month + File.separator + day + File.separator + time + originFile.getName();
        return NewPath;
    }

    /**
     * 关闭流,顺序要传对！！
     */
    public static void CloseStream(Map<String, Object> Streams) {
        for (Iterator iter = Streams.keySet().iterator(); iter.hasNext(); ) {
            String StreamName = (String) iter.next();
            try {
                if (StreamName.toLowerCase().contains(StreamEnum.INPUTSTREAM.getName())) {
                    InputStream Stream = (InputStream) Streams.get(StreamName);
                    if (Stream != null) {
                        Stream.close();
                    }
                }
                else if(StreamName.toLowerCase().contains(StreamEnum.OUTPUTSTREAM.getName())){
                    OutputStream Stream = (OutputStream) Streams.get(StreamName);
                    if (Stream != null) {
                        Stream.close();
                    }
                }
                else if(StreamName.toLowerCase().contains(StreamEnum.READER.getName())){
                    Reader reader= (Reader) Streams.get(StreamName);
                    if(reader!=null){
                        reader.close();
                    }
                }
                else if(StreamName.toLowerCase().contains(StreamEnum.WRITER.getName())){
                    Writer writer= (Writer) Streams.get(StreamName);
                    if(writer!=null){
                        writer.close();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

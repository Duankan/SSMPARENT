package com.ktw.duan.utils;

import com.ktw.duan.base.StreamEnum;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * java Nio
 */
public class NioUtils {
    private ReadableByteChannel rchannel;
    private WritableByteChannel wchannel;
    /**
     * 将数据从一个通道复制到另一个通道或从一个文件复制到另一个文件的示例
     */
    public void CopyDataToChannelOrFile(File orginFile) {
        Map<String,Object> StreamAndChannel=new TreeMap<>();
        try {
            FileInputStream inputStream = new FileInputStream(orginFile);
            String newPath = CreateMidrsAndReturnNewPath(orginFile);
            FileOutputStream outputStream = new FileOutputStream(newPath);
            //获取通道
            rchannel = inputStream.getChannel();
            wchannel = outputStream.getChannel();
            StreamAndChannel.put("wchannel",wchannel);
            StreamAndChannel.put("outputStream",outputStream);
            StreamAndChannel.put("rchannel",rchannel);
            StreamAndChannel.put("inputStream",inputStream);
            copyData(rchannel, wchannel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            Close(StreamAndChannel);
        }
    }

    /**
     * 创建新的文件夹并返回全路径
     */
    public String CreateMidrsAndReturnNewPath(File orginFile) {
        Calendar now = Calendar.getInstance();
        String year = String.valueOf(now.get(Calendar.YEAR));
        String month = String.valueOf(now.get(Calendar.MONTH) + 1);
        String day = String.valueOf(now.get(Calendar.DAY_OF_MONTH));
        String time = String.valueOf(now.getTimeInMillis());
        //创建外面层级文件夹
        String midrspath = StringUtils.substringBefore(orginFile.getPath(), orginFile.getName())
                + year + File.separator + month + File.separator + day + File.separator + "nio";
        File midrs = new File(midrspath);
        midrs.mkdirs();
        String NewPath = StringUtils.substringBefore(orginFile.getPath(), orginFile.getName())
                + year + File.separator + month + File.separator + day + File.separator + "nio" + File.separator + time + orginFile.getName();
        return NewPath;
    }

    /**
     * 复制文件
     */
    public void copyData(ReadableByteChannel rchannel, WritableByteChannel wchannel) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(20*1024);
        try {
            while ((rchannel.read(byteBuffer)) != -1) {
                //缓冲切换到 写 模式
                byteBuffer.flip();
                while (byteBuffer.hasRemaining()) {
                    wchannel.write(byteBuffer);
                }
                byteBuffer.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 释放资源
     */
    public void Close(Map<String,Object> maps){
        for (Iterator iter = maps.keySet().iterator(); iter.hasNext(); ) {
            String name= (String) iter.next();
            try {
                if(name.toLowerCase().contains(StreamEnum.INPUTSTREAM.getName())){
                    InputStream stream= (InputStream) maps.get(name);
                    if(stream!=null){
                        stream.close();
                    }
                }
                if(name.toLowerCase().contains(StreamEnum.OUTPUTSTREAM.getName())){
                    OutputStream stream= (OutputStream) maps.get(name);
                    if(stream!=null){
                        stream.close();
                    }
                }
                if(name.toLowerCase().contains(StreamEnum.RCHANNEL.getName())){
                    ReadableByteChannel channel= (ReadableByteChannel) maps.get(name);
                    if(channel!=null){
                        channel.close();
                    }
                }
                if(name.toLowerCase().contains(StreamEnum.WRCHANNEL.getName())){
                    WritableByteChannel channel= (WritableByteChannel) maps.get(name);
                    if(channel!=null){
                        channel.close();
                    }
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 选择器selector用例
     */
    public void SelectorCase(){

    }
}

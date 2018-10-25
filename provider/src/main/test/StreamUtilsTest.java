import com.ktw.duan.utils.NioUtils;
import com.ktw.duan.utils.StreamUtils;
import org.junit.Test;

import java.io.File;

public class StreamUtilsTest {
    @Test
    public void test() {
        String OriginPath = "C:\\Users\\dankin\\Desktop\\新建文本文档.txt";
        File OriginFile = new File(OriginPath);
        String str=StreamUtils.GetContentByCharBuffer(OriginFile);
        System.out.println(str);
    }

    /**
     * nio的字节复制文件
     */
    @Test
    public void test2(){
        String OriginPath = "C:\\Users\\dankin\\Desktop\\dankin.png";
        File OriginFile = new File(OriginPath);
        new NioUtils().CopyDataToChannelOrFile(OriginFile);
    }
}

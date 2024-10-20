package top.abigtree.im.robot.service.config;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.naming.NameCoder;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDomDriver;
import top.abigtree.im.robot.service.annotation.XStreamCDATA;
import top.abigtree.im.robot.service.models.weixin.InputMessageDTO;

import java.io.Writer;
import java.lang.reflect.Field;

/**
 * @author Shuxin Wang <shuxinwang662@gmail.com>
 * Created on 2024/7/1
 */
public class XStreamFactory {
    public static final String CDATA_PREFIX = "<![CDATA[";
    public static final String CDATA_SUFFIX = "]]>";

    public static XStream createXStream() {
        final NameCoder nameCoder = new NoNameCoder();

        XStream xs = new XStream(new XppDomDriver(nameCoder) {
            @Override
            public HierarchicalStreamWriter createWriter(Writer out) {
                return new PrettyPrintWriter(out, nameCoder) {
                    boolean cdataFlag = false;
                    Class<?> targetClass = null;

                    @Override
                    public void startNode(String name, Class clazz) {
                        super.startNode(name, clazz);
                        if (targetClass == null) {
                            targetClass = clazz;
                        }
                        cdataFlag = isCDATA(targetClass, name);
                    }

                    @Override
                    public void writeText(QuickWriter writer, String text) {
                        if (cdataFlag) {
                            writer.write(CDATA_PREFIX);
                            writer.write(text);
                            writer.write(CDATA_SUFFIX);
                        } else {
                            writer.write(text);
                        }
                    }
                };
            }
        });
        XStream.setupDefaultSecurity(xs);
        xs.allowTypes(new Class[]{
                InputMessageDTO.class
        });
        return xs;
    }

    private static boolean isCDATA(Class<?> clazz, String fieldAlias) {
        //检查类本身
        boolean cdataFlag = isExistCDATA(clazz, fieldAlias);
        if (cdataFlag) {
            return cdataFlag;
        }
        //继续检查父类
        Class<?> superClazz = clazz.getSuperclass();
        while (!superClazz.equals(Object.class)) {
            cdataFlag = isExistCDATA(superClazz, fieldAlias);
            if (cdataFlag) {
                return cdataFlag;
            }
            superClazz = superClazz.getSuperclass();
        }
        return false;
    }

    /**
     * 检查是否有 @XStreamCDATA 注解
     *
     * @param clazz      clazz
     * @param fieldAlias fieldAlias
     */
    private static boolean isExistCDATA(Class<?> clazz, String fieldAlias) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.getAnnotation(XStreamCDATA.class) != null) {
                XStreamAlias xStreamAlias = field.getAnnotation(XStreamAlias.class);
                if (xStreamAlias != null && fieldAlias.equals(xStreamAlias.value())) {
                    return true;
                } else {
                    if (fieldAlias.equals(field.getName())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

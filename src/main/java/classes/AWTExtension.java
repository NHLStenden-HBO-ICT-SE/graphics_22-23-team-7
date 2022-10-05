package classes;

import org.jcodec.codecs.png.PNGDecoder;
import org.jcodec.common.Preconditions;
import org.jcodec.common.VideoCodecMeta;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.model.ColorSpace;
import org.jcodec.common.model.Picture;
import org.jcodec.scale.AWTUtil;
import org.jcodec.scale.ColorUtil;
import org.jcodec.scale.Transform;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
//credits to: https://github.com/jcodec/jcodec/blob/master/src/test/java/org/jcodec/scale/AWTUtil.java
public class AWTExtension extends AWTUtil {
    public static Picture decodePNG(File f, ColorSpace tgtColor) throws IOException {
        Picture picture = decodePNG0(f);
        Preconditions.checkNotNull(picture, "cant decode " + f.getPath());
        return convertColorSpace(picture, tgtColor);
    }

    public static Picture decodePNG0(File f) throws IOException {
        PNGDecoder pngDec = new PNGDecoder();
        ByteBuffer buf = NIOUtils.fetchFromFile(f);
        VideoCodecMeta codecMeta = pngDec.getCodecMeta(buf);
        Picture pic = Picture.create(codecMeta.getSize().getWidth(), codecMeta.getSize().getHeight(),
                ColorSpace.RGB);
        return pngDec.decodeFrame(buf, pic.getData());
    }
    public static Picture convertColorSpace(Picture pic, ColorSpace tgtColor) {
        Transform tr = ColorUtil.getTransform(pic.getColor(), tgtColor);
        Picture res = Picture.create(pic.getWidth(), pic.getHeight(), tgtColor);
        tr.transform(pic, res);
        return res;
    }
}

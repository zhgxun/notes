package github.banana;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 继承FilterInputStream的方式来新增功能, 统计读取到的字节个数
 * 
 * @author zhgxun
 *
 */
public class CountInputStream extends FilterInputStream {
	// 统计读取到的文本字节数
	public int count;

	public CountInputStream(InputStream in) {
		super(in);
	}

	@Override
	public int read(byte[] b, int off, int len) throws IOException {
		int n = super.read(b, off, len);
		count += n;
		return n;
	}

}

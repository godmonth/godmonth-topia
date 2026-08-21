package com.godmonth.topia.basic.servlet.filter;

import org.apache.commons.io.output.TeeOutputStream;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.WriteListener;
import java.io.IOException;
import java.io.OutputStream;

public class TeeServletOutputStream extends ServletOutputStream {

	private final TeeOutputStream teeOutputStream;

	public TeeServletOutputStream(OutputStream first, OutputStream second) {
		teeOutputStream = new TeeOutputStream(first, second);
	}

	@Override
	public void write(int b) throws IOException {
		this.teeOutputStream.write(b);
	}

	public void flush() throws IOException {
		super.flush();
		this.teeOutputStream.flush();
	}

	public void close() throws IOException {
		super.close();
		this.teeOutputStream.close();
	}

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public void setWriteListener(WriteListener listener) {
    }
}

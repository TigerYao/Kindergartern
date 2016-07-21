package com.android.volley;

import android.content.Context;

import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.MultipartRequestParams;

import org.apache.http.HttpEntity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MultipartRequest<T> extends Request<T> {

    private Response.ErrorListener errorListener = null;
    private Response.Listener listener = null;
    private MultipartRequestParams params = null;
    private HttpEntity httpEntity = null;

    public MultipartRequest(String url, MultipartRequestParams params) {
        this(Method.POST, params, url, null, null, null);
    }

    public MultipartRequest(int method, MultipartRequestParams params, String url,
                            Context context, Response.ErrorListener errorListener, Response.Listener listener) {
        super(method, url, null);
        // TODO Auto-generated constructor stub
        this.params = params;
        this.errorListener = errorListener;
        this.listener = listener;
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        // TODO Auto-generated method stub
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        if (params != null) {
            httpEntity = params.getEntity();
            try {
                httpEntity.writeTo(baos);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                //Logger.d("IOException writing to ByteArrayOutputStream");
            }
//			String str = new String(baos.toByteArray());
            //Logger.d("bodyString is :" + str);
        }
        return baos.toByteArray();
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        String json = null;
        try {
            json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            errorListener.onErrorResponse(new ParseError(response));
        }
        return (Response<T>) Response.success(json, HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        // TODO Auto-generated method stub
        Map<String, String> headers = super.getHeaders();
        if (null == headers || headers.equals(Collections.emptyMap())) {
            headers = new HashMap<String, String>();
        }
        return headers;
    }

    @Override
    public String getBodyContentType() {
        // TODO Auto-generated method stub
        String str = httpEntity.getContentType().getValue();
        return httpEntity.getContentType().getValue();
    }

    @Override
    protected void deliverResponse(T response) {
        // TODO Auto-generated method stub
        listener.onResponse(response);
    }

    @Override
    public void deliverError(VolleyError error) {
        // TODO Auto-generated method stub
        if (errorListener != null) {
            errorListener.onErrorResponse(error);
        }
    }
}
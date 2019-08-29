package com.zghzbckj.config;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * <dl>
 * <dt>ZustZSModuleFallBack</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2019</dd>
 * <dd>Company:</dd>
 * <dd>CreateDate: 2019/8/29</dd>
 * </dl>
 *
 * @author xby
 */
@Component
public class ZustZSModuleFallBack implements FallbackProvider {

    @Override
    public String getRoute() {
        return "zustZSModule";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String s, Throwable throwable) {
        return new ZustZSModuleFallBack.ResponseBack(s + ":=" + throwable.getMessage());
    }

    /*返回调用不通的结果*/
    private class ResponseBack implements ClientHttpResponse {
        private String message;

        public ResponseBack(String message) {
            this.message = message;
        }

        @Override
        public HttpStatus getStatusCode() throws IOException {
            return HttpStatus.OK;
        }

        @Override
        public int getRawStatusCode() throws IOException {
            return getStatusCode().value();
        }

        @Override
        public String getStatusText() throws IOException {
            return getStatusCode().getReasonPhrase();
        }

        @Override
        public void close() {

        }

        @Override
        public InputStream getBody() throws IOException {
            return new ByteArrayInputStream(this.message.getBytes());
        }

        @Override
        public HttpHeaders getHeaders() {
            HttpHeaders headers = new HttpHeaders();
            MediaType mt = new MediaType("application", "json", Charset.forName("UTF-8"));
            headers.setContentType(mt);
            return headers;
        }
    }
}
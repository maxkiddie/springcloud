package com.ydy.zuul.fallback;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import com.ydy.base.vo.ResultVo;
import com.ydy.ienum.EnumSystem;
import com.ydy.utils.JsonUtil;

@Component
public class ZuulFallback implements FallbackProvider {

	@Override
	public String getRoute() {
		// TODO Auto-generated method stub
		return "*";
	}

	@Override
	public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
		return new ClientHttpResponse() {
			@Override
			public HttpStatus getStatusCode() {
				return HttpStatus.OK; // 请求网关成功了，所以是ok
			}

			@Override
			public int getRawStatusCode() {
				return HttpStatus.OK.value();
			}

			@Override
			public String getStatusText() {
				return HttpStatus.OK.getReasonPhrase();
			}

			@Override
			public void close() {

			}

			@Override
			public InputStream getBody() throws IOException {
				ResultVo<Object> result = new ResultVo<Object>();
				result.setErrorEnum(EnumSystem.SERVICE_ERROR);
				return new ByteArrayInputStream(JsonUtil.toJson(result).getBytes("UTF-8")); // 返回前端的内容
			}

			@Override
			public HttpHeaders getHeaders() {
				HttpHeaders httpHeaders = new HttpHeaders();
				httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8); // 设置头
				return httpHeaders;
			}
		};
	}

}

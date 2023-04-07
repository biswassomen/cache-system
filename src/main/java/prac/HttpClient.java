package prac;

import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HttpClient {
    CloseableHttpClient client;

    private static final int TIMEOUT_MILLIS = 1000;

    public void init() {
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(TIMEOUT_MILLIS)
                .build();

        client = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
    }

    public HttpResponse executeGet(String url, Map<String, String> headers, Map<String, String> params)
            throws IOException, URISyntaxException {
        HttpGet request = new HttpGet(url);
        addHeaders(request, headers);
        addParams(request, params);
        return execute(request);
    }

    public HttpResponse executePost(String url, Map<String, String> headers, String requestBody)
            throws URISyntaxException, IOException {
        HttpPost request = new HttpPost(url);
        request.setEntity(new StringEntity(requestBody, Consts.UTF_8));
        addHeaders(request, headers);
        return execute(request);
    }

    private HttpResponse execute(HttpUriRequest request) throws IOException {
        try (CloseableHttpResponse response = client.execute(request)) {
            return new HttpResponse(response.getStatusLine().getStatusCode(),
                    EntityUtils.toString(response.getEntity())) {
            };
        }
    }

    private void addHeaders(HttpUriRequest request, Map<String, String> headers) {
        headers.keySet().stream()
                .forEach(headerKey -> request.addHeader(headerKey, headers.get(headerKey)));
    }

    private void addParams(HttpUriRequest request, Map<String, String> params) throws URISyntaxException {
        List<NameValuePair> pairs = params.keySet().stream()
                .map(key -> new BasicNameValuePair(key, params.get(key)))
                .collect(Collectors.toList());
        URI uri = new URIBuilder(request.getURI())
                .addParameters(pairs)
                .build();
        ((HttpRequestBase) request).setURI(uri);
    }

}

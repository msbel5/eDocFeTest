package mocks;

import org.apache.tools.ant.taskdefs.Classloader;
import org.mockserver.client.MockServerClient;

import org.mockserver.matchers.Times;
import org.mockserver.model.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.mockserver.matchers.Times.exactly;
import static org.mockserver.model.BinaryBody.binary;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.model.StringBody.exact;

public class BaseMock {

    //byte[] array = Files.readAllBytes(Paths.get("1_1.svg.gz"));
/*
    ClassLoader classloader =getClass().getClassLoader();
    File file = new File(classloader.getResource("1_1.svg.gz").getFile());
*/

    String filePath = "src" + File.separator + "test" + File.separator + "resources" + File.separator + "media" + File.separator + "1_1.svg.gz";

    byte[] fileBinary;

    {
        try {
            fileBinary = Files.readAllBytes(Paths.get(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    Headers responseHeaders = new Headers(
            new Header("Access-Control-Allow-Origin", "http://192.168.1.35:8080"),
            new Header("Access-Control-Allow-Methods","*"),
            new Header("Access-Control-Allow-Headers","*"),
            new Header("Accept","*"),
            new Header("Accept-Encoding","gzip, deflate"),
            new Header("Accept-Language","en-US,en;q=0.9"),
            new Header("Authorization","Bearer "),
            new Header("Cache-Control","no-cache"),
            new Header("Connection","keep-alive"),
            new Header("Host","localhost:3001"),
            new Header("Origin","http://192.168.1.35:8080"),
            new Header("Pragma","no-cache"),
            new Header("Content-Type","application/json"),
            new Header("Access-Control-Request-Headers","*"),
            new Header("Access-Control-Expose-Headers","*"),
            new Header("Referer","http://192.168.1.35:8080/?id=PPTX-Sample-02&owner=1&storeURI=/home/edoc/nas/data1/edoc/src/test/")
    );


    String body = "{\"convertResult\":1,\"convertedPage\":5,\"docId\":\"SampleData2\",\"docVersion\":\"v1\",\"excelSheetNames\":[\"Instructions\",\"Pivot\",\"SalesOrders\",\"SampleNumbers\",\"MyLinks\"],\"imgHeight\":0,\"imgWidth\":1920,\"md5\":\"E9EDAA2FB324C95CB84419A57B2FA1A4\",\"origFileExtension\":\"xlsx\",\"sheetSizes\":{\"1\":[[\"8504\",\"0\",\"1\",\"1\"],[\"8504\",\"0\",\"2\",\"1\"],[\"8504\",\"0\",\"3\",\"1\"]],\"2\":[[\"13230\",\"0\",\"1\",\"1\"]],\"3\":[[\"14531\",\"0\",\"1\",\"1\"]],\"4\":[[\"12078\",\"0\",\"1\",\"1\"]],\"5\":[[\"24275\",\"1\",\"1\",\"1\"]]},\"statusFlag\":3,\"suffix\":\".svg.gz\",\"totalPage\":5,\"urlPrefix\":\"\",\"zoomFactor\":4}";

    String queryResponse = "/home/edoc/nas/data1/edoc/src/test/";
    String query = "StoreURI";

    public void responseLiteralWithBodyOnly() {
        new MockServerClient("192.168.1.35", 3001)
                .when(
                        request().withMethod("GET")
                                .withPath("ms-preview/api/v2/preview/EDM/1/PPTX-Sample-02/image")
                                .withQueryStringParameter(query,queryResponse)
                )
                .respond(
                        response()
                                .withStatusCode(200)
                                .withBody(body)
                                .withHeaders(responseHeaders)
                );
/*
        new MockServerClient("192.168.1.35", 3001)
                .when(
                        request().withMethod("GET")
                                .withPath("ms-preview/api/v2/preview/EDM/download/1/PPTX-Sample-02/")
                                .withQueryStringParameter(query,queryResponse)
                                .withQueryStringParameter("pageNum","1")
                                .withQueryStringParameter("documentIndex","1")
                )
                .respond(
                        response()
                                .withStatusCode(200)
                                .withBody(binary(fileBinary))
                                .withHeaders(responseHeaders.withEntry("Content-disposition","attachment; filename=1_1_XLS.svg.gz"))
                );
*/
    }

}

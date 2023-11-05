package bedware.tools;

import static bedware.tools.MyBangServer.BangEngine.bang;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import com.sun.net.httpserver.HttpServer;

public class MyBangServer {

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/", (exchange) -> {
            System.out.println("--------------");
            String query = exchange.getRequestURI().getPath().substring("/".length());
            String redirectTo = bang(query);
            exchange.getResponseHeaders().add("Location", redirectTo);
            exchange.sendResponseHeaders(307, 0);
            exchange.close();
        });
        server.start();
        System.out.println("Server started");
    }

    static class BangEngine {

        record BangQuery(String target, String query) {}

        public static String bang(String query) {
            System.out.println("From:" + query);
            query = URLDecoder.decode(query, StandardCharsets.UTF_8);

            BangQuery bang = _bang(query);

            String target = bang.target.replace("%s", URLEncoder.encode(bang.query, StandardCharsets.UTF_8));
            System.out.println("To:" + target);
            return target;
        }

        private static BangQuery _bang(String query) {
            Optional<Bang> selectedEngine = Bang.selectEngineByQuery(query);
            String target;
            if (selectedEngine.isPresent()) {
                var engine = selectedEngine.get();
                query = query.replace(engine.getShortcutWithBangMarker(), "").trim();
                target = engine.uri();
            } else {
                target = Bang.DEFAULT_SEARCH_ENGINE;
            }
            return new BangQuery(target, query);
        }
    }
}

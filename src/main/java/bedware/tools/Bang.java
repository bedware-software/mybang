package bedware.tools;

enum Bang {
    // Searching services
    DUCKDUCKGO("d", "https://duckduckgo.com/?q=%s"),
    GOOGLE("g", "https://google.com/search?q=%s"),
    STACKOVERFLOW("so", "https://duckduckgo.com/?q=%s site:stackoverflow.com"),
    NEOVIMCRAFT("vp", "https://neovimcraft.com/?search=%s"),
    MY_GITHUB_STARS("ghs", "https://github.com/bedware?q=%s&tab=stars"),
    MY_GITHUB_ME("ghme", "https://github.com/bedware/%s"),

    // Apps
    INFOMATE("news", "https://infomate.club/"),
    AI_LIBRARY("ailib", "https://library.phygital.plus/"),
    EXCALIDRAW("draw", "https://excalidraw.com/"),
    BING("bing", "https://www.bing.com/search?q=Bing+AI&showconv=1"),
    DEEL("deel", "https://app.deel.com"),
    GOOGLE_SHEETS("sheets", "https://docs.google.com/spreadsheets/"),
    GOOGLE_DOCUMENTS("docs", "https://docs.google.com/document/");

    static final Bang DEFAULT_SEARCH_ENGINE = Bang.DUCKDUCKGO;

    Bang(String shortcut, String uri) {
        this.shortcut = shortcut;
        this.uri = uri;
    }

    final private String shortcut;
    final private String uri;
    final static String BANG_MARKER = "!";

    public String getShortcut() {
        return BANG_MARKER + shortcut;
    }

    public String getUri() {
        return uri;
    }
}

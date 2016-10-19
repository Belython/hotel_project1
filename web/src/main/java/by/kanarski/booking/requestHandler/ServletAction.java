package by.kanarski.booking.requestHandler;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Дмитрий on 19.07.2016.
 */
public enum ServletAction {
    FORWARD_PAGE, REDIRECT_PAGE, AJAX_REQUEST, CALL_COMMAND;

    private String page;
    private String commandName;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getPageName() {
        String regExp = "\\/\\w+\\.";
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(page);
        String pr = matcher.group();
        String pageName = pr.substring(1, pr.length() - 1);
        return pageName;
    }
}

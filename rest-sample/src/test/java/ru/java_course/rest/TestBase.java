package ru.java_course.rest;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;

import java.io.IOException;

public class TestBase {
    boolean isIssueOpen(int issueId) throws IOException {
        String json = getExecutor()
                .execute(Request.Get("https://bugify.stqa.ru/api/issues/" + issueId + ".json"))
                .returnContent().asString();  //ссылка
        JsonElement issue = new JsonParser().parse(json).getAsJsonObject().get("issues").getAsJsonArray().get(0);
        String state = issue.getAsJsonObject().get("state_name").getAsString();
        System.out.println(issueId + " is " + state);
        if (state.equals("Resolved")) {
            return true;
        } else {
            return false;
        }
    }

    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    public Executor getExecutor() {
        return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");
    }
}
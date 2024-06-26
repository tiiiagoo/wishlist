package br.tgs.utils;

import static com.google.gson.JsonParser.parseReader;
import static java.nio.file.Files.newBufferedReader;
import static java.nio.file.Path.of;

import java.io.BufferedReader;
import java.nio.file.Path;
import lombok.SneakyThrows;

public class JsonUtil {

	protected static Path workingDir = of("", "src/test/resources/json");

	public static String jsonContent(String json) {
		return parseReader(reader(json)).getAsJsonObject().toString();
	}

	public static String jsonArrayContent(String json) {
		return parseReader(reader(json)).getAsJsonArray().toString();
	}

	@SneakyThrows
	protected static BufferedReader reader(String json) {
		return newBufferedReader(workingDir.resolve(json));
	}
}


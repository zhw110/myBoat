package mycode;

import java.util.List;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

public class ReadCsvDatatoMemoryTest {
	@Test
	public final void test() {
		ReadCsvDatatoMemory readCsvDatatoMemory = new ReadCsvDatatoMemory();
		List<JsonFormat> list = readCsvDatatoMemory.list;
		// for (JsonFormat jsonFormat : list) {
		// System.out.println(jsonFormat.getImo());
		// }

		System.out.println(JSON.toJSONString(list));
	}

}

package mypack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;



public class JsonEnumTest {
	
public static void main(String[] args) throws Exception {

	//System.out.println(Test.ERROR1);
	List<Test> list = new ArrayList<Test>();
	list.add(Test.ERROR1);
	list.add(Test.ERROR2);
	list.add(Test.ERROR3);
	
	
	Map<String, List<Test>> map = new HashMap<String, List<Test>>();
	
	
	map.put("errors", list);
	ObjectMapper mapper = new ObjectMapper();
	//String ss = mapper.writeValueAsString(p);
	//System.out.println(ss);
	//JsonObject jsonObject = new JsonObject();
	
	String sss = mapper.writeValueAsString(map);
	System.out.println(sss);
	
	}

}

package mypack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mkyong.rest.Address;
import com.mkyong.rest.Product;

public class JsonFormatTest {
	@SuppressWarnings("rawtypes")
public static void main(String[] args) throws Exception {

	Address address = new Address();
	address.setCity("New Delhi");
	Product product = new Product();
	product.setName("Nokia");
	product.setQty(10);
	product.setAddress(address);
	
	Map map = new HashMap();
	
	List<Product> p = new ArrayList<Product>();
	p.add(product);
	p.add(product);
	//Object object = map;
	map.put("message", "This is test message");
	ObjectMapper mapper = new ObjectMapper();
	//String ss = mapper.writeValueAsString(p);
	//System.out.println(ss);
	//JsonObject jsonObject = new JsonObject();
	
	String sss = mapper.writer().withDefaultPrettyPrinter().writeValueAsString(map);
	System.out.println(sss);
	
	//Gson gson = new Gson();
	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	String s = gson.toJson(p);
	//System.out.println(s);
	
	}

}

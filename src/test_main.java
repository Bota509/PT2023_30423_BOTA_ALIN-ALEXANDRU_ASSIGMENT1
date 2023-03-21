import java.util.HashMap;

public class test_main {

    public static void main(String[] args) {

        //functioneaza ca dictionarele din C#
        HashMap<String,Integer> empId = new HashMap<>();
        empId.put("John", 12);
        empId.put("Carl" ,45);
        empId.put("Alin", 122);
        empId.put("Gge" ,451);
        System.out.println(empId);

        System.out.println(empId.get("Carl"));

        System.out.println(empId.containsKey("Jon"));
        System.out.println(empId.containsValue(12));

        empId.putIfAbsent("La",54); // daca nu vrei sa dai override la un element din hashMap
    }
}

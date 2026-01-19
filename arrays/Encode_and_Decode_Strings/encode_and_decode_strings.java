public class Solution {

  public String encode(List<String> strs){
    StringBuilder res = new StringBuilder();
    for (String s : strs) {
      res.append(s.length()).append('#').append(s);
    }
    return res.toString();
  }
  public List<String> decode(String str) {
    List<String> res = new ArrayList<>();
    int i = 0;
    while (i < str.length()) {
      int j = i; // Find the '#'
      while (str.charAt(j) != '#'){
        j++;
      }
      //Get the length
      int length = Integer.parseInt(str.substring(i, j));

      // Move i to the start of the actual string
      i = j + 1;

      // Extract the string of given len
      j = i + length;
      res.add(str.substring(i, j));

      // Move to the next encoded part
      i = j;
    }
    return res;
  }
}

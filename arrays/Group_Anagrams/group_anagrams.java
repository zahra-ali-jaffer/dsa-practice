class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) return new ArrayList();
        HashMap<String, List> map = new HashMap<>();
        for (String s : strs){
            char[] array = s.toCharArray();
            Arrays.sort(array);
            String Key = String.valueOf(array);
            System.out.println(Key);
            if(!map.containsKey(Key)){
                map.put(Key, new ArrayList());
            }
            map.get(Key).add(s);
        }
        return new ArrayList(map.values());
    }
}

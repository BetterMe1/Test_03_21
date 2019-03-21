package Test_0321;

/*
1014. 在 D 天内送达包裹的能力 
传送带上的包裹必须在 D 天内从一个港口运送到另一个港口。
传送带上的第 i 个包裹的重量为 weights[i]。每一天，我们都会按给出重量的顺序往传送带上装载包裹。
我们装载的重量不会超过船的最大运载重量。
返回能在 D 天内将传送带上的所有包裹送达的船的最低运载能力。

示例 1：
输入：weights = [1,2,3,4,5,6,7,8,9,10], D = 5
输出：15
解释：
船舶最低载重 15 就能够在 5 天内送达所有包裹，如下所示：
第 1 天：1, 2, 3, 4, 5
第 2 天：6, 7
第 3 天：8
第 4 天：9
第 5 天：10
请注意，货物必须按照给定的顺序装运，因此使用载重能力为 14 的船舶并将包装分成 (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) 是不允许的。

示例 2：
输入：weights = [3,2,2,4,1,4], D = 3
输出：6
解释：
船舶最低载重 6 就能够在 3 天内送达所有包裹，如下所示：
第 1 天：3, 2
第 2 天：2, 4
第 3 天：1, 4

示例 3：
输入：weights = [1,2,3,1,1], D = 4
输出：3
解释：
第 1 天：1
第 2 天：2
第 3 天：3
第 4 天：1, 1
提示：
1 <= D <= weights.length <= 50000
1 <= weights[i] <= 500
 */
/*
 * 分析：
 * 分析此题，我们发现可以通过先遍历一遍weights求出不考虑包裹分装以及其顺序等的“最低标准”，
 * “最低标准” = max(weights中所有包裹的重量/天数 , weights中包裹的重量最大值)。
 * 因为最低运载能力一定会大于或等于这两个值。
 * 然后以“最低标准”为基数，验证它是否能在要求天数内完成送达所有包裹，
 * 能完成，返回这个值，不能完成，则加一，直到验证可以在指定天数内完成送达时返回。
 */
public class LeetcodeTest {
	public static void main(String[] args) {
		Solution So = new Solution();
		int[] weights = {1,2,3,1,1};
		int D = 4;
		System.out.println(So.shipWithinDays(weights, D));
	}
}
class Solution {
    public int shipWithinDays(int[] weights, int D) {
        int res = 0;//统计所有包裹的重量
        int max = 0;//包裹中的重量最大值
        for(int i=0; i<weights.length; i++){
            res += weights[i];
            if(max < weights[i]){
                max =  weights[i];
            }
        }
        res = Math.max(res / D , max); // “最低标准”
        while(true){
            int sum = 0;
            int d = 1;//验证天数
            for(int i=0; i<weights.length; i++) {
               if(sum + weights[i] <= res){
                   sum += weights[i];
               }else{
                   sum = weights[i];
                   d++;
               }
            }
            if(d <= D){
                return res;
            }else{
                res++;
            }
        }
    }
}

package isampling.isampling.functions;

	import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
	import java.util.Hashtable;
import java.util.List;
import java.util.Random;
	/**
	 * @author LDIOP
	 *
	 */
	
	
public class FunctionsSamples {
	
	public Double score(List<String> trans, String label, Hashtable<List<String>, Double> conf) {
		Double val = 0.;
		for(List<String> rule:conf.keySet()) {
			if(trans.containsAll(rule.subList(0, rule.size()-1)) && rule.get(rule.size()-1).equals(label))
				val+=conf.get(rule);
		}
		return val;
	}
	
		public static String toString(Hashtable<Integer, String> echantillon) {
			StringBuilder sb = new StringBuilder();
			
			Enumeration<?> e = echantillon.keys();
			while(e.hasMoreElements()){
				Object key = e.nextElement();
				sb.append(echantillon.get(key)+"\n");
			}	
			return sb.toString();
		}
		
		public static int findIndex(List<Double> listVal, int i, int j, float x){
			int m=(i+j)/2;
			//System.out.println(listVal+" "+i+" "+j+" "+x);
			if(m==0 || (listVal.get(m-1)<x && x<=listVal.get(m)))
				return m;
			if(listVal.get(m)<x)
				return findIndex(listVal,m+1,j,x);
			return findIndex(listVal,i,m,x);
		}
		
		//pile ou face
		public static boolean pile(){
			Random rn= new Random();
			return (rn.nextInt(2)==1);
		}
					
////////////////
			
	public static double combinaison(double n, double k){
		//Nombre de combinaisons de n objets pris k a k
		if(k>n || n==0) return 0;
		if(k > n/2)
			k = n-k;
		double x = 1;
		double y = 1;
		double i = n-k+1;
		while(i <= n){
			x = (x*i)/y;
			y += 1;
			i += 1;
		}
		//print "x",x
		return x;
	}
	
	public static int Ksize(List<Double> crible, String util, Integer m, Integer M, Double alpha){
		List<Double> listElelm=new ArrayList<Double>();
		Double som=0.0;
		if(util.equals("freq"))
			for(int i=0; i<crible.size(); i++){
				som+=crible.get(i);
				listElelm.add(som);
			}
		else if(util.equals("area"))
			for(int i=0; i<crible.size(); i++){
				som+=crible.get(i)*(i+m);
				listElelm.add(som);
			}
		else if(util.equals("weight"))
			for(int i=0; i<crible.size(); i++){
				som+=crible.get(i)*Math.pow(alpha,i+m);
				listElelm.add(som);
			}
		else if(util.equals("gauss"))
			for(int i=0; i<crible.size(); i++){
				som+=crible.get(i)*Gauss(i+m, m, M);
				listElelm.add(som);
			}
		else {
			System.out.println("Undefinite utility");
			System.exit(1);
		}
		int i=0, j=listElelm.size();
		float alea= (float)(Math.random()*som);
		int k=findIndex(listElelm,i,j,alea);
		return k+1;
	}
	
	public static String ListToString(Integer[] maxLength) {
		String out = "{";
		Integer i=0;
		for(; i<maxLength.length-1; i++)
			out=out+maxLength[i].toString()+", ";
		return out+maxLength[i].toString()+"}";
	}
	
	public static int sum(List<Double> crible){
		int som=0,i;
		for(i=0; i<crible.size();i++)
			som+=crible.get(i);
		return som;
	}
	
	public static Double maxLis(List<Double> listDouble){
		Double max=0.0;
		for(int i=0; i<listDouble.size();i++)
			max = Math.max(max,listDouble.get(i));
		return max;
	}
	
	//nombre de sous-ensemble d'un itemset 
	public static List<Double> phi(int sizeOfItemset, int m, int M,String util, Double alpha){
		List<Double>  ListWeight= new ArrayList<Double>();
		if(M==0 || sizeOfItemset==0 || m>M){
			ListWeight.add(0.0);
			return ListWeight;
		}
		int k=Math.min(M,sizeOfItemset);
		if(util.equals("freq"))
			for (int i=m; i<=k; i++){
				ListWeight.add(combinaison(sizeOfItemset,i));
			}
		else if(util.equals("area"))
			for (int i=m; i<=k; i++){
				ListWeight.add(combinaison(sizeOfItemset,i)*i);
			}
		else if(util.equals("weight"))
			for (int i=m; i<=k; i++){
				ListWeight.add(combinaison(sizeOfItemset,i)*Math.pow(alpha, i));
			}
		else if(util.equals("gauss")) {
			for (int i=m; i<=k; i++){
				ListWeight.add(combinaison(sizeOfItemset,i)*Gauss(i, m, M));
			}
		}
		else {
			System.out.println("Fonction d'utilité indéfinie");
			System.exit(1);
		}
		return ListWeight;
	}
	
	public static double Gauss(int i, int m, int M) {
		double x=1/Math.sqrt(Math.PI*(M-m));
		double y=Math.pow((M+m-2*i),2)/(4*(M-m));
		return x*Math.exp(-y);
	}
	
	//Moyenne
	public static double Mean(List<Double> arrayAccuracy) {
		double meanSum=0.;
		for (int i = 0; i < arrayAccuracy.size(); i++) 
			meanSum += arrayAccuracy.get(i); 
	    return meanSum/arrayAccuracy.size(); 
	}
	
	//Ecart-type
	public static double EcartType(double mean, List<Double> arrayAccuracy) {
		double deviationSum=0;
		double arrayVar[] = new double[arrayAccuracy.size()];
	    for (int i = 0; i < arrayAccuracy.size(); i++) 
	    	arrayVar[i] = (Math.pow((arrayAccuracy.get(i) - mean), 2)); 
	    for (int i = 0; i < arrayAccuracy.size(); i++) 
	    	deviationSum += arrayVar[i]; 
	    double variance = ((deviationSum/arrayAccuracy.size())); 
	    return ((int)(Math.sqrt(variance)*100))/100.; 
	}
	
	public static void recordSample(String directory, Hashtable<List<String>, Integer> sample, String nameDataset) throws IOException {
		BufferedWriter printerAvecBuffer = null;
		try{
			printerAvecBuffer = new BufferedWriter(
					new FileWriter(directory+"\\"+nameDataset+".TXT", false));
		}
		catch(FileNotFoundException exc){
			System.out.println("Erreur d'ecriture"+exc.toString()+"\nNot found directory : "+directory+"\\"+nameDataset+".TXT");
		}
		String output="";
		for(List<String> pattern:sample.keySet()) {
			output = output+pattern.toString()+"\t"+sample.get(pattern)+"\n";
		}
		printerAvecBuffer.write(output, 0, output.length());
		printerAvecBuffer.close();
	}
}

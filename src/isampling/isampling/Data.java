package isampling.isampling;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.regex.Pattern;

import isampling.isampling.functions.FunctionsSamples;

public class Data {
	private Hashtable<Integer,List<String>> dataset;
	private List<Double> weightedData;
	private Hashtable<Integer,List<Double>> weightedDataCrible;
	private Hashtable<String,List<Integer>> classData;
	private Double sumWeight = 0.0;
	private String nameDataset;
	private Double preprocessingTime = 0.0;
    final Pattern separator = Pattern.compile("[\t , /]");
	
    public Data(String pathData, Integer m, Integer M, boolean ContainClass, String util, Double alpha) throws IOException {
		String[] namePath = separator.split(pathData);
		this.nameDataset = namePath[namePath.length-2];
		this.dataset = new Hashtable<Integer,List<String>>();
		this.weightedDataCrible = new Hashtable<Integer,List<Double>>();
		this.weightedData = new ArrayList<Double>();
		if(ContainClass)
			this.classData = new Hashtable<String,List<Integer>>();
		BufferedReader lecteurAvecBuffer = null;
		try{
			lecteurAvecBuffer = new BufferedReader(
					new FileReader(pathData));
		}
		catch(FileNotFoundException exc){
			System.out.println("Erreur d'ouverture"+exc.toString());
		}
		
		Integer key=0;
		String ligne;
		while ((ligne = lecteurAvecBuffer.readLine()) != null){
			double ext = System.currentTimeMillis();
			String [] items= separator.split(ligne);
			List<String> itemset = new ArrayList<String>();
			if(ContainClass) {
				List<Integer> listKey;
				if(this.classData.containsKey(items[items.length-1]))
					listKey = this.classData.get(items[items.length-1]);
				else
					listKey = new ArrayList<Integer>();
				listKey.add(key);
					this.classData.put(items[items.length-1], listKey);
			}
			for(int i=0;i<items.length;i++) itemset.add(items[i]);
			this.dataset.put(key, itemset);
			List<Double> crible = FunctionsSamples.phi(itemset.size()-1, m, M, util,alpha);
			this.weightedDataCrible.put(key, crible);
			this.sumWeight += FunctionsSamples.sum(crible);
			this.weightedData.add(sumWeight);
			key++;
			this.preprocessingTime += System.currentTimeMillis()-ext;
		}
	}
    
    public Data(Data data, List<Integer> indices) {
    	this.nameDataset = data.getNameDataset();
		this.dataset = new Hashtable<Integer,List<String>>();
		this.weightedDataCrible = new Hashtable<Integer,List<Double>>();
		this.weightedData = new ArrayList<Double>();
		Integer key=0;
    	for(Integer l:indices) {
    		this.dataset.put(key, data.getDataset().get(l));
    		this.weightedDataCrible.put(key, data.getWeightedDataCrible().get(l));
			this.sumWeight += FunctionsSamples.sum(data.getWeightedDataCrible().get(l));
			this.weightedData.add(sumWeight);
			key++;
    	}
    }
	

	public Hashtable<Integer, List<String>> getDataset() {
		return dataset;
	}

	public List<Double> getWeightedData() {
		return weightedData;
	}

	public Hashtable<Integer, List<Double>> getWeightedDataCrible() {
		return weightedDataCrible;
	}

	public Hashtable<String, List<Integer>> getClassData() {
		return classData;
	}

	public Double getSumWeight() {
		return sumWeight;
	}
	
	public String getNameDataset() {
		return nameDataset;
	}

	public Double getPreprocessingTime() {
		return preprocessingTime;
	}
	
	public void addAll(Hashtable<Integer, List<String>> dataset) {
		this.dataset.putAll(dataset);
	}
	
}

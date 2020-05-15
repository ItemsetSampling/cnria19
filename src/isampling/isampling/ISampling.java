package isampling.isampling;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

import isampling.isampling.functions.FunctionsSamples;

public class ISampling {
	private Hashtable<List<String>, Integer> sample = new Hashtable<List<String>, Integer>();
	private Double samplingTime = 0.0;
	
	public ISampling(Integer sampleSize, Data data, Integer minSize, Integer maxSize, String util, Double alpha){
		double start = System.currentTimeMillis();
		for(Integer i=0; i<sampleSize; i++){
			Float val= (float) (Math.random()*data.getSumWeight());
			int k=0, j=data.getDataset().size();
			Integer indice=FunctionsSamples.findIndex(data.getWeightedData(), k, j, val);
			List<String> trans = data.getDataset().get(indice);
			List<String> motif= new ArrayList<String>();
			int x=FunctionsSamples.Ksize(data.getWeightedDataCrible().get(indice),util,minSize, maxSize, alpha)+Math.max(0, minSize-1);
			List<Integer>  T=new ArrayList<Integer>();
			for(int g=0; g<trans.size()-1; g++) T.add(g);
			Integer l;
			Random rn= new Random();
			for(int f=0;f<x;f++){
				l=rn.nextInt(T.size());
				motif.add(trans.get(T.get(l)));
				T.remove(T.get(l));
			}
			//motif.add(indice.toString());
			if(this.sample.containsKey(motif)) {
				this.sample.put(motif,this.sample.get(motif)+1);
			}else
				this.sample.put(motif,1);
		}
		this.samplingTime += System.currentTimeMillis() - start;
	}

	public Hashtable<List<String>, Integer> getEchantillon() {
		return sample;
	}

	public Double getSamplingTime() {
		return samplingTime;
	}
	
}

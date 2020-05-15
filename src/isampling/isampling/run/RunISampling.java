package isampling.isampling.run;

import java.io.File;
import java.util.Hashtable;
import java.util.List;
import java.util.regex.Pattern;

import isampling.isampling.Data;
import isampling.isampling.ISampling;
import isampling.isampling.functions.FunctionsSamples;

public class RunISampling {

	public static void main(String[] args) throws Exception {
	    final Pattern separator = Pattern.compile("[\t , ./]");
		//Integer sampleSize=100;
		Integer minPatternLength= 1;//Minimal length constraint
		String pathDatasets = "Input/";
		String pathSamples = "Output/";
		File pathFile = new File(pathDatasets);
		String[] datasets = pathFile.list();
		String[] utilities = {"area","freq","weight"};
		String util = utilities[1];
		Integer [] sizeOfSamples = {1000};
		Integer sampleSize = sizeOfSamples[0];
		Integer [] maxLength = {2,3,4,5,6,7};
		//Double alpha = 0.017;
		boolean recordSample=true;
		System.out.println("*******************************************************************************\n"
						+ "* version 1.0\n"
						+ "* Date : 20/12/2018\n"
						+ "* Goal : Paper for the National conference CNRIA 2019\n"
						+ "* Sample size : "+sampleSize.toString()+"\n"
						+ "* Utility : "+util+"\n"
						+ "* Minimal length constraint : "+minPatternLength.toString()+" by default\n"
						+ "* Maximal length constraint : in "+FunctionsSamples.ListToString(maxLength)+"\n"
						+ "*                          GOOD LUCK!!\n"
						+ "*******************************************************************************");
		for(String dataset:datasets) {
			String nameDataset = separator.split(dataset)[0];
			System.out.println(nameDataset);
			String pathData = pathDatasets+dataset;
			for(Integer maxPatternLength:maxLength) {
				System.out.println("\tM = "+maxPatternLength.toString());
				Data data = new Data(pathData, minPatternLength, maxPatternLength, true, util,alpha);
				System.out.println("\t\tPreprocessing time : "+((int)(Math.round((data.getPreprocessingTime())*1000)))/1000.+" ms");
				ISampling iSampling = new ISampling(sampleSize, data, minPatternLength, maxPatternLength, util, alpha);
				System.out.println("\t\tSampling time : "+((int)(Math.round((iSampling.getSamplingTime())*1000)))/1000.+" ms");
				if(recordSample) {
					Hashtable<List<String>, Integer> sample = iSampling.getEchantillon();
					FunctionsSamples.recordSample(pathSamples, sample, nameDataset+"_M"+maxPatternLength);
					System.out.println("\t\tDistinct sampled patterns : "+sample.size());
				}
			}
		}
		System.out.println("************************************ END *************************************");
	}

}

package test;

import org.jeecgframework.codegenerate.window.CodeWindow;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;


/**
 * 【单表模型】代码生成器入口
 * @author 张代浩
 * @site www.jeecg.org
 *
 */
public class JeecgOneGUI extends RecursiveTask<Integer> {

	/*public static void main(String[] args) {
		CodeWindow  codeWindow = new CodeWindow();
		codeWindow.pack();
	}*/



	private static final int THREAD_HOLD = 2;

	private int start;
	private int end;

	public JeecgOneGUI(int start,int end){
		this.start = start;
		this.end = end;
	}

	@Override
	protected Integer compute() {
		int sum = 0;
		//如果任务足够小就计算
		boolean canCompute = (end - start) <= THREAD_HOLD;
		if(canCompute){
			for(int i=start;i<=end;i++){
				sum += i;
			}
		}else{
			int middle = (start + end) / 2;
			JeecgOneGUI left = new JeecgOneGUI(start,middle);
			JeecgOneGUI right = new JeecgOneGUI(middle+1,end);
			//执行子任务
			left.fork();
			right.fork();
			//获取子任务结果
			int lResult = left.join();
			int rResult = right.join();
			sum = lResult + rResult;
		}
		return sum;
	}


}

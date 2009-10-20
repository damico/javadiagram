package org.jdamico.javadiagram.creator;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.jdamico.javadiagram.config.Constants;
import org.jdamico.javadiagram.dataobject.RetanguleBox;


public class DiagramCreator {
	
	private Graphics2D g2dFS;
	private BufferedImage bufferedImage = null;
	private Map<Integer,Integer> grid = new HashMap<Integer, Integer>();
	public ArrayList<RetanguleBox> retArray = new ArrayList<RetanguleBox>();
	private Map<Integer,Integer>  lineCarrier = new HashMap<Integer, Integer>();
	private String targetFileName;
	
	public DiagramCreator(String targetFileName) {
		this.targetFileName = targetFileName;
	}

	public void design(ArrayList<RetanguleBox> retArray){
		
		this.retArray = retArray;
		
		grid = getFlowMap();
		
		bufferedImage = new BufferedImage(500,(grid.size()*(Constants.DEFAULT_BOX_HEIGTH+Constants.YMARGING))+Constants.YMARGING, BufferedImage.TYPE_INT_RGB);
		
		g2dFS = bufferedImage.createGraphics();
		int x = 0;
		int y = 0;
		int flines = grid.size();
		int centerX =0;
		int menor = 10000;
		int maior = -10000;
		for(int i = 0; i < flines; i++){
		
			int lcolumns = grid.get(i);
			y = i * (Constants.DEFAULT_BOX_HEIGTH+Constants.YMARGING)+Constants.YMARGING;
			for(int j = lcolumns; 0 <= j; j--){
				x = j * (Constants.DEFAULT_BOX_LENGTH);//(100+10)+10
				centerX = x + (Constants.DEFAULT_BOX_LENGTH/2);
				int k = save(i,j,x,y,centerX);
				lineCarrier.put(k,lcolumns*Constants.DEFAULT_BOX_LENGTH);
			}
		
		}
		
		
		for(int k = 1; k < retArray.size(); k++){
			
				RetanguleBox aux = retArray.get(k);
				int source = aux.getSource();
				RetanguleBox s = retArray.get(source);
				int sCenter = s.getCenter();
				int carrier = lineCarrier.get(k);
				int nx = 0;
				int ox =  aux.getX();
				nx = (-1*(carrier/2 - sCenter))+ox;
				aux.setX(nx);
				aux.setCenter(nx+(Constants.DEFAULT_BOX_LENGTH/2));
			
			System.out.println(retArray.get(k).getCaption()+" | ROW: "+retArray.get(k).getRow()+ " | SC["+source+"]: "+sCenter+" | NX: "+nx+" | OX: "+ox+" | CARRIER: "+carrier);

			if(nx < menor) menor = nx;
			if(nx > maior)  maior = nx;
			
		}
		
		bufferedImage = new BufferedImage(maior+(menor * -1)+Constants.DEFAULT_BOX_LENGTH+(2*Constants.XMARGING),(grid.size()*(30+Constants.YMARGING))+Constants.YMARGING, BufferedImage.TYPE_INT_RGB);
		g2dFS = bufferedImage.createGraphics();
		
		for(int k = 0; k < retArray.size(); k++){
			
			retArray.get(k).setX(retArray.get(k).getX() + (menor * -1) +  Constants.XMARGING);
			retArray.get(k).setCenter(retArray.get(k).getCenter() + (menor * -1) +  Constants.XMARGING);

			int x1 = getSourceCenterX(retArray.get(k).getSource());
			int ldiff = getLdiff(retArray.get(k));
			int y1 = retArray.get(k).getY();
			int x2 = x1;
			int y2 = y1 - (ldiff);
			g2dFS.setColor(Color.gray);
			g2dFS.drawLine(x1, y1, x2, y2);
			
		}

		
		for(int k = 0; k < retArray.size(); k++){

			int nx = retArray.get(k).getX();
			g2dFS.drawRect(nx, retArray.get(k).getY(), retArray.get(k).getLenght(), retArray.get(k).getHeight());
			g2dFS.setColor(Color.GRAY);
			g2dFS.fillRect(nx, retArray.get(k).getY(), retArray.get(k).getLenght(), retArray.get(k).getHeight());
			g2dFS.setColor(Color.DARK_GRAY);
			g2dFS.drawRect(nx, retArray.get(k).getY(), retArray.get(k).getLenght(), retArray.get(k).getHeight());
			g2dFS.drawString(retArray.get(k).getCaption(), nx+(Constants.DEFAULT_BOX_LENGTH/2), retArray.get(k).getY()+15);
			
		}
		
		write2FS(getImage(),targetFileName);
		
	}
	
	private int getLdiff(RetanguleBox retanguleSource){
		int ldiff = 0;
		int id = retanguleSource.getSource();
		if(id>=0) ldiff = retanguleSource.getRow() - retArray.get(id).getRow();
		if(ldiff > 1) ldiff = (ldiff*Constants.YMARGING)+((ldiff-1)*Constants.DEFAULT_BOX_HEIGTH);
		else ldiff = ldiff * Constants.YMARGING;
		return ldiff;
	}

	private int getSourceCenterX(int source) {
		int centerX = 0;
		for(int k = 0; k < retArray.size(); k++){
			if(retArray.get(k).getId() == source){
				
				centerX = retArray.get(k).getCenter();
			}
		}
		
		
		return centerX;
	}

	private int save(int i, int j, int x, int y, int centerX) {
		int ret = -1;
		for(int k = 0; k < retArray.size(); k++){
			if(retArray.get(k).getRow() == i && retArray.get(k).getCol() == j){
				retArray.get(k).setX(x);
				retArray.get(k).setY(y);
				retArray.get(k).setCenter(centerX);
				ret = k;
				break;
			}
		}
		return ret;
	}

	private void write2FS(RenderedImage rendImage,String path){
        try {
            File file = new File(path);
            ImageIO.write(rendImage, "png", file);
        } catch (IOException ioe) {
        	ioe.printStackTrace();
        }
    }
	
	private RenderedImage getImage() {
		
        g2dFS.dispose();   
        return bufferedImage;
    }
	
	private Map<Integer,Integer> getFlowMap() {
		
		Map<Integer,Integer> mapFlow =  new HashMap<Integer, Integer>();
		
		for(int i = 0; i < retArray.size(); i++){
			mapFlow.put(retArray.get(i).getRow(), retArray.get(i).getCol());
		}
		int c = 1;
		for(int j =0; j < mapFlow.size(); j++){
			for(int i = 0; i < retArray.size(); i++){
				
				if(retArray.get(i).getRow()==j){
					
					mapFlow.put(j,c++);
				} 
			}
			c=1;
		}
		
		return mapFlow;
	}
}

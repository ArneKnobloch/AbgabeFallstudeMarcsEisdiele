/** 
 * Ext GWT 3.0.0-rc2 - Ext for GWT 
 * Copyright(c) 2007-2012, Sencha, Inc. 
 * licensing@sencha.com 
 * 
 * http://sencha.com/license 
 */
package marcsEisdiele.client.diagramm; 
  
import java.util.ArrayList; 
import java.util.List; 

  
import com.google.gwt.core.client.GWT; 
import com.google.gwt.dom.client.Style.Unit; 
import com.google.gwt.editor.client.Editor.Path; 
import com.google.gwt.event.logical.shared.ValueChangeEvent; 
import com.google.gwt.event.logical.shared.ValueChangeHandler; 
import com.google.gwt.user.client.ui.IsWidget; 
import com.google.gwt.user.client.ui.Widget; 
import com.sencha.gxt.chart.client.chart.Chart; 
import com.sencha.gxt.chart.client.chart.Chart.Position; 
import com.sencha.gxt.chart.client.chart.Legend; 
import com.sencha.gxt.chart.client.chart.axis.CategoryAxis; 
import com.sencha.gxt.chart.client.chart.axis.NumericAxis; 
import com.sencha.gxt.chart.client.chart.series.BarSeries; 
import com.sencha.gxt.chart.client.chart.series.Series.LabelPosition; 
import com.sencha.gxt.chart.client.chart.series.SeriesLabelConfig; 
import com.sencha.gxt.chart.client.draw.RGB; 
import com.sencha.gxt.chart.client.draw.sprite.TextSprite; 
import com.sencha.gxt.core.client.ValueProvider; 
import com.sencha.gxt.data.shared.ListStore; 
import com.sencha.gxt.data.shared.ModelKeyProvider; 
import com.sencha.gxt.data.shared.PropertyAccess; 
import com.sencha.gxt.fx.client.Draggable; 
import com.sencha.gxt.widget.core.client.ContentPanel; 
import com.sencha.gxt.widget.core.client.FramedPanel; 
import com.sencha.gxt.widget.core.client.Resizable; 
import com.sencha.gxt.widget.core.client.button.TextButton; 
import com.sencha.gxt.widget.core.client.button.ToggleButton; 
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer; 
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData; 
import com.sencha.gxt.widget.core.client.event.SelectEvent; 
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler; 
import com.sencha.gxt.widget.core.client.toolbar.ToolBar; 
  
public class ImageChartExample implements IsWidget { 
  
  public interface DataPropertyAccess extends PropertyAccess<Data> { 
    ValueProvider<Data, Double> data1(); 
  
    ValueProvider<Data, Double> data2(); 
  
    ValueProvider<Data, Double> data3(); 

    ValueProvider<Data, Double> data4(); 
    
    ValueProvider<Data, String> name(); 
  
    @Path("name") 
    ModelKeyProvider<Data> nameKey(); 
  } 
  
  private static final DataPropertyAccess dataAccess = GWT.create(DataPropertyAccess.class); 
	ContentPanel cp = new ContentPanel();
  
  @Override
  public Widget asWidget() {   
	  
    return cp; 
  } 
  
  public ImageChartExample(String header, String name0, String name1, String name2, String name3, final int[] werte, int maxWert) { 
	    final ListStore<Data> store = new ListStore<Data>(dataAccess.nameKey()); 
	    store.addAll(TestData.getData(werte)); 
	  
	    final Chart<Data> chart = new Chart<Data>(); 
	    chart.setStore(store); 
	    chart.setShadow(true); 
	  
	    NumericAxis<Data> axis = new NumericAxis<Data>(); 
	    axis.setPosition(Position.LEFT); 
	    axis.addField(dataAccess.data1()); 
	    axis.addField(dataAccess.data2()); 
	    axis.addField(dataAccess.data3()); 
	    axis.addField(dataAccess.data4()); 
	    TextSprite title = new TextSprite(header); 
	    title.setFontSize(18); 
	    axis.setTitleConfig(title); 
	    axis.setDisplayGrid(true); 
	    axis.setMinimum(0); 
	    axis.setMaximum(maxWert); 
	    chart.addAxis(axis); 
	  
	    CategoryAxis<Data, String> catAxis = new CategoryAxis<Data, String>(); 
	    catAxis.setPosition(Position.BOTTOM); 
	    catAxis.setField(dataAccess.name()); 
	    title = new TextSprite("Quartale"); 
	    title.setFontSize(18); 
	    catAxis.setTitleConfig(title); 
	    chart.addAxis(catAxis); 
	  
	    final BarSeries<Data> bar = new BarSeries<Data>(); 
	    bar.setYAxisPosition(Position.LEFT); 
	    bar.addYField(dataAccess.data1()); 
	    bar.addYField(dataAccess.data2()); 
	    bar.addYField(dataAccess.data3()); 
	    bar.addYField(dataAccess.data4()); 
	    bar.addColor(new RGB(241, 251, 150)); 
	    bar.addColor(new RGB(190, 220, 248)); 
	    bar.addColor(new RGB(248, 190, 196)); 
	    bar.addColor(new RGB(180, 238, 180)); 
	    bar.setColumn(true); 
	    SeriesLabelConfig<Data> config = new SeriesLabelConfig<Data>(); 
	    config.setLabelPosition(LabelPosition.END); 

	    bar.setLabelConfig(config); 
	    List<String> legendTitles = new ArrayList<String>(); 
	    legendTitles.add(name0); 
	    legendTitles.add(name1); 
	    legendTitles.add(name2); 
	    legendTitles.add(name3); 
	    bar.setLegendTitles(legendTitles); 
	    chart.addSeries(bar); 
	  
	    final Legend<Data> legend = new Legend<Data>(); 
	    legend.setPosition(Position.RIGHT); 
	    legend.setItemHighlighting(true); 
	    legend.setItemHiding(true); 
//	    legend.setMarkerRenderer(legendImageRenderer); 
	    chart.setLegend(legend); 
	 
	  
	    ContentPanel panel = new FramedPanel(); 
	    panel.getElement().getStyle().setMargin(10, Unit.PX); 
	    panel.setCollapsible(true); 
	    panel.setHeadingText(header); 
	    panel.setPixelSize(620, 500); 
	    panel.setBodyBorder(true); 
	    panel.collapse();

	    new Draggable(panel, panel.getHeader()).setUseProxy(false); 
	  
	    VerticalLayoutContainer layout = new VerticalLayoutContainer(); 
	    panel.add(layout); 
	  
	    chart.setLayoutData(new VerticalLayoutData(1, 1)); 
	    layout.add(chart); 
	    cp = panel;

  }


} 

 
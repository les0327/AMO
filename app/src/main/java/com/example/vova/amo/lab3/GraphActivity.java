package com.example.vova.amo.lab3;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.vova.amo.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.List;

public class GraphActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        Interpolation interpolation = (Interpolation) getIntent().getSerializableExtra("object");

        float a = interpolation.getA();
        float b = interpolation.getB();
        float h = interpolation.getH();

        GraphView graphF = (GraphView) findViewById(R.id.graph1);
        GraphView graphI = (GraphView) findViewById(R.id.graph2);

        List<DataPoint> functionPoints      = new ArrayList<>();
        List<DataPoint> interpolationPoints = new ArrayList<>();

        while (a < b){
            functionPoints.add(new DataPoint(a, interpolation.valueAt(a)));
            interpolationPoints.add(new DataPoint(a, interpolation.interpolate(a)));
            a += h/3;
        }

        LineGraphSeries<DataPoint> seriesF = new LineGraphSeries<>(functionPoints.toArray(new DataPoint[0]));
        graphF.addSeries(seriesF);

        LineGraphSeries<DataPoint> seriesI = new LineGraphSeries<>(interpolationPoints.toArray(new DataPoint[0]));
        graphI.addSeries(seriesI);

    }
}

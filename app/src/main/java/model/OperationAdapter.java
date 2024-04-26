package model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.finalproject_couplepoints.R;

import java.util.ArrayList;

public class OperationAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Operation> operationList;


    Operation operation;

    public OperationAdapter(Context context, ArrayList<Operation> operationList) {
        this.context = context;
        this.operationList = operationList;

    }



    @Override
    public int getCount() {
        return operationList.size();
    }

    @Override
    public Object getItem(int position) {
        return operationList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //getView is responsible to show the item in ListView
        //if the item has many widgets --> yuo need to include them

        View oneItem;
        TextView tvMessage, tvDate, tvPoints;

        //To access to one_item.xml file --> we need to do an operation
        // called LayoutInflation
        // LayoutInflation: means convert xml fiel to View object
        operation = operationList.get(position);

        LayoutInflater inflater = LayoutInflater.from(context);
        oneItem = inflater.inflate(R.layout.one_item,parent,false);

        // Access to each item in one_item.xml
        tvMessage = oneItem.findViewById(R.id.tvMessage);
        tvDate = oneItem.findViewById(R.id.tvDate);
        tvPoints = oneItem.findViewById(R.id.tvPoints);

        //Populate the info of on_item.xml


        tvMessage.setText(operation.getMessage());
        tvDate.setText(operation.getDate().toString());
        int points = operation.getPoints();
        String type = String.valueOf(operation.getType());
        String pointsToShow = "";
        if(type.equals(OperationType.GOOD_POINTS.toString()) ){
            pointsToShow = "+";
        }
        else{
            pointsToShow = "-";
        }
        pointsToShow += String.valueOf(points);
        tvPoints.setText(String.valueOf(pointsToShow));


        return oneItem;
        //return null;
    }
}

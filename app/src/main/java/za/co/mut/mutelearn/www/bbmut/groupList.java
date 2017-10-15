package za.co.mut.mutelearn.www.bbmut;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by minenhle on 2017/09/19.
 */

public class groupList extends ArrayAdapter<pojo_groups>{

    private Activity context;
    List<pojo_groups> groupsList;



    public groupList(Activity context, List<pojo_groups> groupsList) {
        super(context, R.layout.list_group, groupsList);
        this.context = context;
        this.groupsList = groupsList;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_group, null, true);


        TextView textName = (TextView) listViewItem.findViewById(R.id.groupName);


        pojo_groups group = groupsList.get(position);

        textName.setText(group.getGroupName());


        return listViewItem;
    }
}






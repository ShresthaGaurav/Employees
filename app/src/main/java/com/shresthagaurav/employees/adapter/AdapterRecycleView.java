package com.shresthagaurav.employees.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shresthagaurav.employees.R;
import com.shresthagaurav.employees.model.Employee;

import java.util.List;

public class AdapterRecycleView extends RecyclerView.Adapter<AdapterRecycleView.ContactsViewHolder > {
    Context context;
    List<Employee> show_employees;

    public AdapterRecycleView(Context context, List<Employee> show_employees) {
        this.context = context;
        this.show_employees = show_employees;
    }

    @NonNull
    @Override
    public AdapterRecycleView.ContactsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_employees, parent, false);
        return new ContactsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRecycleView.ContactsViewHolder holder, int position) {

        final Employee se = show_employees.get(position);
        holder.txtid.append(String.valueOf(se.getId()));
        holder.txtname.append(se.getEmployee_name());
        holder.txtsalary.append(String.valueOf(se.getEmployee_salary()));
        holder.txtage.append(String.valueOf(se.getEmployee_age()));

    }

    @Override
    public int getItemCount() {
        return show_employees.size();
    }

    public class ContactsViewHolder extends RecyclerView.ViewHolder {
        TextView txtname,txtage,txtid,txtsalary;
        public ContactsViewHolder(@NonNull View itemView) {
            super(itemView);
            txtname=itemView.findViewById(R.id.View_name);
            txtid=itemView.findViewById(R.id.View_id);
            txtsalary=itemView.findViewById(R.id.View_salary);
            txtage=itemView.findViewById(R.id.View_age);
        }
    }

}
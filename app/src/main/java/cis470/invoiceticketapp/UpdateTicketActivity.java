package cis470.invoiceticketapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class UpdateTicketActivity extends AppCompatActivity {


    Ticket ticket = new Ticket();
    EditText Name, DateCreated, Problem, Status, DateFixed;
    static ArrayList<Ticket> ticketList =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_ticket);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            ticket = extras.getParcelable("TICKET");
        }

        Name = (EditText)findViewById(R.id.update_name);
        Name.setText(ticket.getCustomerName());
        ticket.setCustomerName(Name.getText().toString());

        DateCreated = (EditText)findViewById(R.id.update_date_created);
        String date_created = ticket.dateToString(ticket.getTicketCreateDate());
        DateCreated.setText(date_created);

        Problem = (EditText)findViewById(R.id.update_problem);
        Problem.setText(ticket.getProblem());

        Status = (EditText)findViewById(R.id.update_status);
        Status.setText(ticket.getStatus());

        DateFixed = (EditText)findViewById(R.id.update_date_fixed);
        String date_fixed = ticket.dateToString(ticket.getFixDate());
        DateFixed.setText(date_fixed);


    }

    public void updateTicket(View v) {

        ticket.setTicketId(ticketList.indexOf(ticket));

        EditText Name = (EditText)findViewById(R.id.name);
        ticket.setCustomerName(Name.getText().toString());

        DateCreated = (EditText)findViewById(R.id.date_created);
        String date_created = new String(DateCreated.toString());
        ticket.setTicketCreateDate(ticket.stringToDate(date_created));

        EditText Problem = (EditText)findViewById(R.id.problem);
        ticket.setProblem(Problem.getText().toString());

        EditText Status = (EditText)findViewById(R.id.status);
        ticket.setStatus(Status.getText().toString());

        DateFixed = (EditText)findViewById(R.id.date_fixed);
        String date_fixed = new String(DateFixed.toString());
        ticket.setFixDate(ticket.stringToDate(date_fixed));

        Intent intent = new Intent(UpdateTicketActivity.this, ListTicketsActivity.class);
        intent.putParcelableArrayListExtra("TICKETS",ticketList );
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_action_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.action_addTieckt){
            startActivity(new Intent(UpdateTicketActivity.this, CreateNewTicketActivity.class));
            return true;
        }
        if(item.getItemId()==R.id.action_ticketList){
            startActivity(new Intent(UpdateTicketActivity.this, ListTicketsActivity.class));
            return true;
        }
        if(item.getItemId()==R.id.action_settings){
            Toast.makeText(this, "There are no settings yet, Sorry.", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


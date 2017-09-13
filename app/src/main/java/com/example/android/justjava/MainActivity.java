/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 */

package com.example.android.justjava;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int quantity = 1;
    boolean checkBoxCream;
    boolean checkBoxChocolate;



    // CheckBox checkBox = (CheckBox) findViewById(R.id.check_box);
    // CheckBox checked = (CheckBox) findViewById(R.id.check_box);
    // boolean checkBox = checked.isChecked();
    public void isChecked(View view) {
        // boolean checkBox = false;
        checkBoxCream = ((CheckBox) findViewById(R.id.check_box_cream)).isChecked();
        checkBoxChocolate = ((CheckBox) findViewById(R.id.check_box_chocolate)).isChecked();
    }

    public String userName() {
        EditText nameInput = (EditText) findViewById(R.id.text_edit);
        String name = nameInput.getText().toString();
        return name;
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        // displayMessage(createOrderSummary(calculatePrice(checkBoxChocolate, checkBoxCream), checkBoxCream, checkBoxChocolate, userName()));
        composeEmail();
    }

    /*
    * This method creates order Summary
     */
    private String createOrderSummary(int price, boolean hasWhippedCream, boolean hasChocolate, String name) {
        String message;
        message = getString(R.string.imie) + name;
        message = message + "\n" + getString(R.string.whipped_cream) + hasWhippedCream;
        message = message + "\n" + getString(R.string.add_chocolate) + hasChocolate;
        message = message + "\n" + getString(R.string.qtity) + quantity;
        message = message + "\n" + getString(R.string.total) + price;
        message = message + "\n" + getString(R.string.thanks);
        return message;
    }

    /*
    This method creates an e-mail intent
     */
    public void composeEmail() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        // intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.mail_title) + userName());
        intent.putExtra(Intent.EXTRA_TEXT, createOrderSummary(calculatePrice(checkBoxChocolate, checkBoxCream), checkBoxCream, checkBoxChocolate, userName()));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * Calculates the price of the order.
     */
    private int calculatePrice(boolean checkBoxChocolate, boolean checkBoxCream) {
        int itemPrice = 5;
        if (checkBoxChocolate) {
            itemPrice = itemPrice + 2;
        }
        if (checkBoxCream) {
            itemPrice = itemPrice + 1;
        }
        int price = quantity * itemPrice;
        return price;
    }

    /*
    This method increases quantity when plus button is clicked
     */
    public void increase (View view) {
        quantity = quantity + 1;
        String toastText = getString(R.string.max_toast);
        if (quantity > 10) {
            quantity = 10;
            displayToast(toastText);
        }
        displayPlus(quantity);
    }

    /*
    This method decreases qunatity when minus button is clicked
     */
    public void decrease (View view) {
        quantity = quantity - 1;
        String toastText = getString(R.string.min_toast);
        if (quantity < 1) {
            quantity = 1;
            displayToast(toastText);
        }
        displayPlus(quantity);
        // displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int liczba) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + liczba);
    }

    private void displayPlus(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private void displayToast (String toastText) {
        Context context = getApplicationContext();
        CharSequence text = toastText;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    /**
     * This method displays the given text on the screen.
     */
//    private void displayMessage(String message) {
//        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
//        orderSummaryTextView.setText(message);
//    }
}
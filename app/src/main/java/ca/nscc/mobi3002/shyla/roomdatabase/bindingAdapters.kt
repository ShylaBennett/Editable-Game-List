package ca.nscc.mobi3002.shyla.roomdatabase

import android.R
import android.widget.EditText

import android.widget.ImageView
import androidx.core.widget.addTextChangedListener
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener


@BindingAdapter("gameImage")
fun loadImage(image: ImageView, resourceID: Int) {
    image.setImageResource(resourceID)
}

//Create getters and setters
//Set float value as String.
@BindingAdapter("floatValue")
fun setFloatInView(view: EditText, value: Float) {
    view.setText(value.toString())
}
//InverseBindingAdapter used as getter to read the Float value from an EditText.
@InverseBindingAdapter(attribute = "floatValue")
fun getFloatFromView(view: EditText): Float {
    //Used toFloatOrNull() ?: 0f, if not used crashes everytime you erase all text.
    return view.text.toString().toFloatOrNull() ?: 0f
}

//Notify two-way binding when text changes
@BindingAdapter("floatValueAttrChanged")
fun setFloatListener(view: EditText, listener: InverseBindingListener?) {
    //If no listener is provided, do nothing.
    if (listener == null) return
    //Every time the text changes, call listener.onChange() to notify data binding.
    view.addTextChangedListener { listener.onChange() }
}




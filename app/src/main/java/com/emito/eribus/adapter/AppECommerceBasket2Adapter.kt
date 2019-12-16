package com.emito.eribus.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.emito.eribus.R
import com.emito.eribus.model.Users
import com.emito.eribus.utils.Utils

import kotlinx.android.synthetic.main.route_detail_activity.view.*


class AppECommerceUsers2Adapter(private val UsersList: List<Users>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var itemClickListener: OnItemClickListener
    private lateinit var currency: String
    private var total = 0

    interface OnItemClickListener {
        fun onItemClick(view: View, obj: Users, position: Int)

        fun onDeleteClick(view: View, obj: Users, position: Int)

        fun onPriceChange(currency: String, subTotal: Int)
    }

    fun setOnItemClickListener(mItemClickListener: OnItemClickListener) {
        this.itemClickListener = mItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.route_detail_activity, parent, false)

        return PlaceViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {

        if (viewHolder is PlaceViewHolder) {

            //val currentUser = UsersList[position]
           // currency = Users.currency
          //  viewHolder.itemNameTextView.text = Users.userName

            val context = viewHolder.holderCardView.context

            val id = Utils.getDrawableInt(context, R.drawable.profile1)
            Utils.setImageToImageView(context, viewHolder.itemImageView, id)


           // val priceStr = Users.currency + " " + Users.price
            //viewHolder.priceTextView.text = priceStr

//            var attributeStr = Users.size
//
//            if (attributeStr != "") {
//                attributeStr += ", "
//            }
//
//            attributeStr += Users.color
            //viewHolder.attributeTextView.text = attributeStr

//            try {
//                val value = Integer.parseInt(viewHolder.qtyEditText.text.toString())
//                val price = Integer.parseInt(Users.price)
//                val subTotal = value * price
//                val subTotalStr = Users.currency + " " + subTotal
//
//                total += subTotal
//
//                viewHolder.subTotalTextView.text = subTotalStr
//            } catch (ignored: Exception) {
//            }
//
//            viewHolder.minusImageView.setOnClickListener {
//
//                try {
//                    var value = Integer.parseInt(viewHolder.qtyEditText.text.toString())
//
//                    if (value > 1) {
//                        value -= 1
//                    }
//
//                    viewHolder.qtyEditText.setText(value.toString())
//
//                    val itemPriceStr = viewHolder.priceTextView.text.toString()
//                    if (itemPriceStr != "") {
//
//                        val price = convertPriceStrToInt(itemPriceStr)
//                        val originalSubTotal = convertPriceStrToInt(viewHolder.subTotalTextView.text.toString())
//                        total -= originalSubTotal
//
//                        val subTotal = value * price
//                        val subTotalStr = Users.currency + " " + subTotal
//                        viewHolder.subTotalTextView.text = subTotalStr
//
//                        total += subTotal
//                        itemClickListener.onPriceChange(Users.currency, total)
//
//                    }
//
//                } catch (ignored: Exception) {
//                }
//            }
//
//            viewHolder.plusImageView.setOnClickListener {
//
//                try {
//                    var value = Integer.parseInt(viewHolder.qtyEditText.text.toString())
//
//                    value += 1
//
//                    viewHolder.qtyEditText.setText(value.toString())
//
//                    val itemPriceStr = viewHolder.priceTextView.text.toString()
//                    if (itemPriceStr != "") {
//
//                        val price = convertPriceStrToInt(itemPriceStr)
//                        val originalSubTotal = convertPriceStrToInt(viewHolder.subTotalTextView.text.toString())
//                        total -= originalSubTotal
//
//                        val subTotal = value * price
//                        val subTotalStr = Users.currency + " " + subTotal
//                        viewHolder.subTotalTextView.text = subTotalStr
//
//                        total += subTotal
//                        itemClickListener.onPriceChange(Users.currency, total)
//
//                    }
//
//                } catch (ignored: Exception) {
//                }
//            }

        }
    }

    private fun convertPriceStrToInt(priceStr: String): Int {
        var price = 0
        try {
            val lPriceStr = priceStr.replace(currency, "").replace(" ", "")
            price = Integer.parseInt(lPriceStr)
        } catch (ignored: Exception) {
        }

        return price
    }

    override fun getItemCount(): Int {
        return UsersList.size
    }

    inner class PlaceViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        internal var itemImageView: ImageView = view.ivUserProfile
        //internal var itemNameTextView: TextView = view.itemNameTextView
        internal var priceTextView: TextView = view.emailTextView
        internal var holderCardView: CardView = view.holderCardView
        internal var deleteImageView: ImageView = view.deleteImageView
        internal var subTotalTextView: TextView = view.tvUserType
        internal var attributeTextView: TextView = view.tvRemark

    }
}
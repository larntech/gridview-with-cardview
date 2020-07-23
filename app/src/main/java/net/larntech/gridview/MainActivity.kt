package net.larntech.gridview

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row_grid_item.view.*

class MainActivity : AppCompatActivity() {

    val imageList = arrayOf(

            ImageModal(R.drawable.image1, "image1"),
            ImageModal(R.drawable.image2, "image2"),
            ImageModal(R.drawable.image3, "image3"),
            ImageModal(R.drawable.image4, "image4"),
            ImageModal(R.drawable.image5, "image5"),
            ImageModal(R.drawable.image6, "image6"),
            ImageModal(R.drawable.image7, "image7"),
            ImageModal(R.drawable.image8, "image8")

    )

    var myImagesList = ArrayList<ImageModal>()

    var customAdapter: CustomAdapter? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for(images in imageList){
            myImagesList.add(images);
        }

        customAdapter = CustomAdapter(myImagesList,this);

        gridView.adapter = customAdapter

        gridView.setOnItemClickListener { adapterView, view, i, l ->

            val imageModal = myImagesList[i];

            when(imageModal.name){
                "image1"->
                    startActivity(Intent(this@MainActivity, Image1Activity::class.java))
                "image2" ->
                    startActivity(Intent(this@MainActivity, Image2Activity::class.java))

                else ->{
                    Toast.makeText(this@MainActivity, "No Action", Toast.LENGTH_LONG).show()
                }
            }

        }


    }

    class CustomAdapter(
            var imageList:ArrayList<ImageModal>,
            var context: Context
    ) : BaseAdapter(){


        override fun getView(position: Int, p1: View?, viwGroup: ViewGroup?): View {
            var view = p1;
            if(view == null){
                view = LayoutInflater.from(context).inflate(R.layout.row_grid_item, viwGroup, false);
            }

            var imageView = view!!.imageName;
            var tvName = view!!.tvName;

            val imageModal = imageList[position];

            imageView.setImageResource(imageModal.image)
            tvName.text = imageModal.name

            return view!!;
        }

        override fun getItem(position: Int): Any {
           return  imageList[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
           return imageList.size
        }

    }
}
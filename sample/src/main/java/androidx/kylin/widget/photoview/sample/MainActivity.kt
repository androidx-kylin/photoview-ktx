package androidx.kylin.widget.photoview.sample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.kylin.widget.photoview.PhotoView
import androidx.kylin.widget.photoview.sample.databinding.ActivityMainBinding
import androidx.kylin.widget.photoview.sample.databinding.PhotoViewItemBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.photoViewPager.adapter = PhotoPagerAdapter(
            listOf(
                "https://images.unsplash.com/photo-1459262838948-3e2de6c1ec80?q=80&w=2969&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                "https://images.unsplash.com/photo-1632813161361-d3a822fd5c26?w=900&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8OHx8ZnVsbCUyMGhkJTIwd2FsbHBhcGVyfGVufDB8fDB8fHww",
                "https://images.unsplash.com/photo-1708893634094-f6604d94e43f?w=900&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTh8fGZ1bGwlMjBoZCUyMHdhbGxwYXBlcnxlbnwwfHwwfHx8MA%3D%3D",
                "https://plus.unsplash.com/premium_photo-1674086970773-726e445f5802?w=900&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTN8fGZ1bGwlMjBoZCUyMHdhbGxwYXBlcnxlbnwwfHwwfHx8MA%3D%3D",
                "https://images.unsplash.com/photo-1635074921144-a78163c43f35?w=900&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTF8fGZ1bGwlMjBoZCUyMHdhbGxwYXBlcnxlbnwwfHwwfHx8MA%3D%3D",
                "https://images.unsplash.com/photo-1652197881268-d625ad54402b?w=900&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MjB8fGdhbWluZ3xlbnwwfHwwfHx8MA%3D%3D",
                "https://images.unsplash.com/photo-1721332155545-c7a8005a2581?w=900&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDF8MHxzZWFyY2h8MjJ8fGdhbWluZ3xlbnwwfHwwfHx8MA%3D%3D",
            )
        )


    }
}

class PhotoPagerAdapter(
    private val items: List<String>
) : RecyclerView.Adapter<PhotoPagerAdapter.PhotoViewHolder>() {


    class PhotoViewHolder(binding: PhotoViewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val photoView: PhotoView = binding.photoLayout.photoView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(
            PhotoViewItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.photoView.maximumScale = 10f
        Glide.with(holder.itemView.context)
            .load(items[position])
            .into(holder.photoView)
    }

    override fun getItemCount(): Int = items.size
}

package com.example.pizza

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.ActionProvider
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.ShareActionProvider
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuItemCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    private var shareActionProvider: ShareActionProvider? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    override fun onResume() {
        super.onResume()
        val pagerAdapter = SectionsPagerAdapter(supportFragmentManager, applicationContext)
        val pager: ViewPager = findViewById(R.id.pager)
        pager.adapter = pagerAdapter

        val tabLayout: TabLayout = findViewById(R.id.tabs)
        tabLayout.setupWithViewPager(pager)
    }

    override fun onStop() {
        super.onStop()
        val pager: ViewPager = findViewById(R.id.pager)
        pager.adapter = null
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean { // Inflate the menu; this adds items to the app bar.
        menuInflater.inflate(R.menu.menu_main, menu)
        val menuItem = menu.findItem(R.id.action_share)
        shareActionProvider = MenuItemCompat.getActionProvider(menuItem) as ShareActionProvider?
        setShareActionIntent("Want to join me for pizza?")
        return super.onCreateOptionsMenu(menu)
    }

    private fun setShareActionIntent(text: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, text)
        shareActionProvider?.setShareIntent(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_create_order -> {
                val intent = Intent(this, OrderActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private class SectionsPagerAdapter(fm: FragmentManager, var context: Context) : FragmentPagerAdapter(fm) {
        override fun getCount(): Int {
            return  4
        }

        override fun getItem(i: Int): Fragment {
            return when (i) {
                0 -> TopFragment()
                1 -> PizzaFragment()
                2 -> PastaFragment()
                3 -> StoreFragment()
                else -> TopFragment()
            }
        }

        override fun getPageTitle(i: Int): CharSequence? {
            return when (i) {
                0 -> context.resources.getText(R.string.home_tab)
                1 -> context.resources.getText(R.string.pizza_tab)
                2 -> context.resources.getText(R.string.pasta_tab)
                3 -> context.resources.getText(R.string.store_tab)
                else -> null
            }
        }
    }
}

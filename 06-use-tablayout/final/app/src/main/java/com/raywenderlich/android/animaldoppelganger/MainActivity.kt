/*
 * Copyright (c) 2020 Razeware LLC
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 * 
 * This project and source code may use libraries or frameworks that are
 * released under various Open-Source licenses. Use of those libraries and
 * frameworks are governed by their own individual licenses.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.android.animaldoppelganger

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator

/**
 * Main Screen
 */
class MainActivity : AppCompatActivity() {

  private lateinit var doppelgangerNamesArray: Array<String>

  var doppelgangerPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
    override fun onPageSelected(position: Int) {
      Toast.makeText(this@MainActivity, "Selected position: ${position}",
          Toast.LENGTH_SHORT).show()
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    // Switch to AppTheme for displaying the activity
    setTheme(R.style.AppTheme)

    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    doppelgangerNamesArray = resources.getStringArray(R.array.doppelganger_names)

    val doppelgangerAdapter = DoppelgangerAdapter(this, doppelgangerNamesArray.size)
    doppelgangerViewPager.adapter = doppelgangerAdapter
    doppelgangerViewPager.registerOnPageChangeCallback(doppelgangerPageChangeCallback)
    TabLayoutMediator(tabLayout, doppelgangerViewPager){tab, position ->
      tab.text = doppelgangerNamesArray[position].split(" ")[0]
    }.attach()

  }

  override fun onDestroy() {
    super.onDestroy()
    doppelgangerViewPager.unregisterOnPageChangeCallback(doppelgangerPageChangeCallback)
  }
}










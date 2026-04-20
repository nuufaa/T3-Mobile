package psti.nufa.designui

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etNama: EditText
    private lateinit var radioGender: RadioGroup
    private lateinit var rbLaki: RadioButton
    private lateinit var rbPerempuan: RadioButton
    private lateinit var cbTravelling: CheckBox
    private lateinit var cbBelanja: CheckBox
    private lateinit var cbMenyanyi: CheckBox
    private lateinit var btnTampilkan: Button
    private lateinit var tvHasil: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inisialisasiView()
        aturKlikButton()
    }

    private fun inisialisasiView() {
        etNama = findViewById(R.id.etNama)
        radioGender = findViewById(R.id.radioGender)
        rbLaki = findViewById(R.id.rbLaki)
        rbPerempuan = findViewById(R.id.rbPerempuan)
        cbTravelling = findViewById(R.id.cbTravelling)
        cbBelanja = findViewById(R.id.cbBelanja)
        cbMenyanyi = findViewById(R.id.cbMenyanyi)
        btnTampilkan = findViewById(R.id.btnTampilkan)
        tvHasil = findViewById(R.id.tvHasil)
    }

    private fun aturKlikButton() {
        btnTampilkan.setOnClickListener {
            prosesInput()
        }
    }

    private fun prosesInput() {
        val nama = etNama.text.toString().trim()

        etNama.error = null
        var valid = true

        if (nama.isEmpty()) {
            etNama.error = "Nama tidak boleh kosong"
            Toast.makeText(this, "Nama tidak boleh kosong!", Toast.LENGTH_SHORT).show()
            valid = false
        }

        val selectedGenderId = radioGender.checkedRadioButtonId
        if (selectedGenderId == -1) {
            Toast.makeText(this, "Jenis kelamin harus dipilih!", Toast.LENGTH_SHORT).show()
            valid = false
        }

        val hobiList = mutableListOf<String>()
        if (cbTravelling.isChecked) hobiList.add("Travelling")
        if (cbBelanja.isChecked) hobiList.add("Belanja")
        if (cbMenyanyi.isChecked) hobiList.add("Menyanyi")

        if (hobiList.isEmpty()) {
            Toast.makeText(this, "Minimal pilih 1 hobi!", Toast.LENGTH_SHORT).show()
            valid = false
        }

        if (!valid) return

        val gender = findViewById<RadioButton>(selectedGenderId).text.toString()

        val hasil = """
        Nama : $nama
        Kelamin : $gender
        Hobi : ${hobiList.joinToString(", ")}
    """.trimIndent()

        tvHasil.text = hasil
    }
}
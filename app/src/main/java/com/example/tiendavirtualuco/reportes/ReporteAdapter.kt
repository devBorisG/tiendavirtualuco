package com.example.tiendavirtualuco.reportes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.widget.ImageView
import android.widget.TextView
import com.example.tiendavirtualuco.R


class ReporteAdapter(private val reportList: List<Report>) : RecyclerView.Adapter<ReporteAdapter.ReportViewHolder>() {
    class ReportViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val reportCodeLabel: TextView = itemView.findViewById(R.id.report_code_label)
        val reportCode: TextView = itemView.findViewById(R.id.report_code)
        val reportStatus: TextView = itemView.findViewById(R.id.report_status)
        val reportDate: TextView = itemView.findViewById(R.id.report_date)
        val reportMethod: TextView = itemView.findViewById(R.id.report_method)
        val reportTracking: TextView = itemView.findViewById(R.id.report_tracking)
        val reportImage: ImageView = itemView.findViewById(R.id.report_image)
        val reportProduct: TextView = itemView.findViewById(R.id.report_product)
        val reportPrice: TextView = itemView.findViewById(R.id.report_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_element, parent, false)
        return ReportViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReportViewHolder, position: Int) {
        val report = reportList[position]
        holder.reportCodeLabel.text = "CÓDIGO DE REPORTE"
        holder.reportCode.text = report.code
        holder.reportStatus.text = report.status
        holder.reportDate.text = report.date
        holder.reportMethod.text = report.method
        holder.reportTracking.text = report.tracking
        holder.reportImage.setImageResource(R.drawable.ic_launcher_background) // Cambia esto según tu imagen
        holder.reportProduct.text = report.product
        holder.reportPrice.text = report.price
    }

    override fun getItemCount() = reportList.size
}
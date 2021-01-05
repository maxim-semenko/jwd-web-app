<script src="https://cdnjs.cloudflare.com/ajax/libs/html2pdf.js/0.9.2/html2pdf.bundle.js"></script>
<script>
    window.onload = function () {
        document.getElementById("download")
            .addEventListener("click", () => {
                const invoice = this.document.getElementById("to_pdf");
                console.log(invoice);
                console.log(window);
                var opt = {
                    margin: 1,
                    filename: 'list.pdf',
                    image: {type: 'jpeg', quality: 0.98},
                    html2canvas: {scale: 2},
                    jsPDF: {unit: 'in', format: 'letter', orientation: 'portrait'}
                };
                html2pdf().from(invoice).set(opt).save();
            })
    }
</script>


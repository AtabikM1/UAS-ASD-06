public class TransaksiPajak {
    int kode;
    long nominalBayar, denda;
    int bulanBayar;
    Kendaraan kendaraan;

    TransaksiPajak(int kode, long nominalBayar, long denda, int bulanBayar, Kendaraan kendaraan) {
        this.kode = kode;
        this.nominalBayar = nominalBayar;
        this.denda = denda;
        this.bulanBayar = bulanBayar;
        this.kendaraan = kendaraan;
    }

    void printInfo() {
        System.out.println("Kode: " + kode);
        System.out.println("Nominal Bayar: " + nominalBayar);
        System.out.println("Denda: " + denda);
        System.out.println("Bulan Bayar: " + bulanBayar);
        System.out.println("Kendaraan: " + kendaraan.noTNKB + ", " + kendaraan.nama + ", " + kendaraan.jenis);
    }
}

class TransaksiNode {
    TransaksiPajak transaksi;
    TransaksiNode next;

    TransaksiNode(TransaksiPajak transaksi) {
        this.transaksi = transaksi;
        this.next = null;
    }
}

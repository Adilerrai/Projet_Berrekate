import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-listrestaurant',
  templateUrl: './listrestaurant.component.html',
  styleUrls: ['./listrestaurant.component.css']
})
export class ListrestaurantComponent implements OnInit, AfterViewInit {
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  
  ventes: any[] = []; // Array to hold the data
  dataSource = new MatTableDataSource<any>(this.ventes); // Initialize with any array
  
  displayedColumns: string[] = ['id', 'prenom', 'nom', 'telephone', 'tarif', 'date_creation', 'status', 'payer', 'actions'];

  constructor() { }

  ngOnInit(): void {
    this.loadData();
  }

  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator; // Attach the paginator after view initialization
  }

  loadData(): void {
    const storedVentes = localStorage.getItem('currentventes');
    if (storedVentes) {
      this.ventes = JSON.parse(storedVentes);
      this.dataSource.data = this.ventes; // Set the data source's data
    } else {
      console.warn('No data found in localStorage under "currentventes".');
      // Handle case where no data is present. Maybe fetch from a server or set default values.
    }
  }
}

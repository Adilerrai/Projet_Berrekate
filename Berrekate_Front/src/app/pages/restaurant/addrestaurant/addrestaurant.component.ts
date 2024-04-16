import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { ApidbService } from 'src/app/services/apidb.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-addrestaurant',
  templateUrl: './addrestaurant.component.html',
  styleUrls: ['./addrestaurant.component.css']
})
export class AddrestaurantComponent implements OnInit {
  RestaurantForm: FormGroup;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  
  tableVente: [] = [];
  ventes:any[]=[];
  currentventes: any = JSON.parse(localStorage.getItem('currentventes') || '{}');
  dataSource = new MatTableDataSource(this.tableVente);

  displayedColumns: string[] = ['id','prenom', 'nom','telephone','tarif','date_creation','status','payer','actions'];
  constructor(
    private apidb: ApidbService,

  ) { 
    this.RestaurantForm = new FormGroup({
      name: new FormControl('', [Validators.required]),
      address: new FormControl('', [Validators.required]),
      contact: new FormControl('', [Validators.required]),
      website: new FormControl('', [Validators.required]),
      details: new FormControl('', [Validators.required]),
    });
  }

  ngOnInit(): void {
  }
  ajouterrestaurant() {
    
    // if (this.VenteForm.valid) {
    //   debugger
     var name = this.RestaurantForm.get('name')?.value;
     var address= this.RestaurantForm.get('address')?.value;
     var contact= this.RestaurantForm.get('contact')?.value;
     var website= this.RestaurantForm.get('website')?.value;
     var details= this.RestaurantForm.get('details')?.value;

      //-------------
      this.apidb.addEnregistrement('vente/', {
        "name":name ,
        "address":address,
        "contact":contact,
        "website":website,
        "details":details,

      }).subscribe(
        (data) => {
          Swal.fire('', 'Restaurant ajouté avec succés', 'success');
          this.RestaurantForm.reset();
        },
        err => {
          Swal.fire('', 'Erreur d\'ajout', 'error');
        }
      );
    }

}

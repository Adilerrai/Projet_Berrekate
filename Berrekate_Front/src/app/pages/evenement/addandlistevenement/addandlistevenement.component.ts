import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { ApidbService } from 'src/app/services/apidb.service';

@Component({
  selector: 'app-addandlistevenement',
  templateUrl: './addandlistevenement.component.html',
  styleUrls: ['./addandlistevenement.component.css']
})
export class AddandlistevenementComponent implements OnInit {
  EvenementForm: FormGroup;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  
  tableVente: [] = [];
  ventes:any[]=[];
  currentventes: any = JSON.parse(localStorage.getItem('currentventes') || '{}');
  dataSource = new MatTableDataSource(this.tableVente);

  displayedColumns: string[] = ['id','prenom', 'nom','telephone','tarif','date_creation','status','payer','actions'];
  constructor(
    private apidb: ApidbService,
  ) { 
    this.EvenementForm = new FormGroup({
      name: new FormControl('', [Validators.required]),
      city: new FormControl('', [Validators.required]),
      country: new FormControl('', [Validators.required]),
      address: new FormControl('', [Validators.required]),
    });
  }

  ngOnInit(): void {
  }

}

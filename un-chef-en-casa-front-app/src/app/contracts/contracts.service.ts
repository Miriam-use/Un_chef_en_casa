
//var Web3 = require('web3');

// import Web3 from 'web3';
//
// export class ContractsService {
//
//   private web3 = new Web3(new Web3.providers.HttpProvider('https://rinkeby.infura.io/v3/ec6f7958e25544428de2b91c4ef76b60'));
//   private abi: any;
//   private address = '0xEc305889Dd07Bb56695Bef0De89723c045C696a5';
//   private contract:any;
//
//   constructor(){}
//
//   async init() {
//     this.abi = require('./abigail.json').abi;
//
//     this.contract = new this.web3.eth.Contract(this.abi, this.address, {gas: 1000000, gasPrice: '10000000000000'});
//
//     this.contract.methods.registrarViaje("ciaAqui",this.address,true,"facturaAqui",12,"hashProveedorAqui","huellaCo2Aqui").call()
//       .then((_receipt: any) => {
//         console.log(_receipt);
//       }).catch((_err: any) => {
//       console.error(_err);
//     });
//   }
// }




// declare let require: any;
// declare let window: any;
// private accounts: string[];
// this.accounts = await this.web3.eth.getAccounts();
// import { Inject, Injectable } from '@angular/core';
// @Injectable({
//   providedIn: 'root'
// })

// ,
// "browser": {
//     "crypto": false,
//     "http":false,
//     "https":false,
//     "stream":false,
//     "os":false
// }

// constructor(@Inject(WEB3) private web3: Web3) {
//   this.init().then(res => {
//   }).catch(err => {
//     console.error(err);
//   });
// }

















// import { Injectable } from '@angular/core';
// const Web3 = require('web3');
// //import Web3 from 'web3';
// //import * as Web3c from 'angular-web3-contract';
// //import * as Web3 from 'web3';
//
// declare let require: any;
// declare let window: any;
// const tokenAbi = require('./abigail.json');
//
//
// @Injectable({
//   providedIn: 'root'
// })
// export class ContractsService {
//
//   web3: any;
//   enable:any;
//   account="0xEc305889Dd07Bb56695Bef0De89723c045C696a5";
//
//   //private _tokenContractAddress: string = "0xbc84f3bf7dd607a37f9e5848a6333e6c188d926c";
//
//   constructor() {
//     if (window.ethereum === undefined) {
//       alert('Non-Ethereum browser detected. Install MetaMask');
//     } else {
//       if (typeof window.web3 !== 'undefined') {
//         this.web3 = window.web3.currentProvider;
//       } else {
//         this.web3 = new Web3.providers.HttpProvider('http://localhost:8545');
//       }
//       console.log('transfer.service :: constructor :: window.ethereum');
//       window.web3 = new Web3(window.ethereum);
//       console.log('transfer.service :: constructor :: this.web3');
//       console.log(this.web3);
//       this.enable = this.enableMetaMaskAccount();
//     }
//   }
//
//   private async enableMetaMaskAccount(): Promise<any> {
//     let enable = false;
//     await new Promise((resolve, reject) => {
//       enable = window.ethereum.enable();
//     });
//     return Promise.resolve(enable);
//   }
//
//   private async getAccount(): Promise<any> {
//     console.log('transfer.service :: getAccount :: start');
//     if (this.account == null) {
//       this.account = await new Promise((resolve, reject) => {
//         console.log('transfer.service :: getAccount :: eth');
//         console.log(window.web3.eth);
//         window.web3.eth.getAccounts((err, retAccount) => {
//           console.log('transfer.service :: getAccount: retAccount');
//           console.log(retAccount);
//           if (retAccount.length > 0) {
//             this.account = retAccount[0];
//             resolve(this.account);
//           } else {
//             alert('transfer.service :: getAccount :: no accounts found.');
//             reject('No accounts found.');
//           }
//           if (err != null) {
//             alert('transfer.service :: getAccount :: error retrieving account');
//             reject('Error retrieving account');
//           }
//         });
//       }) as Promise<any>;
//     }
//     return Promise.resolve(this.account);
//   }
//
//   transferEther(value) {
//     const that = this;
//     console.log('transfer.service :: transferEther to: ' +
//       value.transferAddress + ', from: ' + that.account + ', amount: ' + value.amount);
//     return new Promise((resolve, reject) => {
//       console.log('transfer.service :: transferEther :: tokenAbi');
//       console.log(tokenAbi);
//       const contract = require('@truffle/contract');
//       const transferContract = contract(tokenAbi);
//       transferContract.setProvider(that.web3);
//       console.log('transfer.service :: transferEther :: transferContract');
//       console.log(transferContract);
//       transferContract.deployed().then(function(instance) {
//         return instance.pay(
//           value.transferAddress,
//           {
//             from: that.account,
//             value: value.amount
//           });
//       }).then(function(status) {
//         if (status) {
//           return resolve({status: true});
//         }
//       }).catch(function(error) {
//         console.log(error);
//         return reject('transfer.service error');
//       });
//     });
//   }
//
//
//
// }

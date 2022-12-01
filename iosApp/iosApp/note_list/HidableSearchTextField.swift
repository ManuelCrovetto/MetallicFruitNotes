//
//  HidableSearchTextField.swift
//  iosApp
//
//  Created by Manuel Crovetto on 15/11/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct HidableSearchTextField<Destination: View>: View {
    
    var onSearchToggled: () -> Void
    var destinationProvider: () -> Destination
    var isSearchActive: Bool
    @Binding var searchText: String
    
    var body: some View {
        HStack {
            TextField("Search...", text: $searchText)
                .textFieldStyle(.roundedBorder)
                .opacity(isSearchActive ? 1 : 0)
                
            if !isSearchActive {
                Spacer()
            }
            
            Button(action: onSearchToggled) {
                Image(systemName: isSearchActive ? "xmark" : "magnifyingglass")
                    .foregroundColor(.black)
            }
            NavigationLink(destination: destinationProvider()) {
                Image(systemName: "plus")
                    .foregroundColor(.black)
            }
        }
    }
}

struct HidableSearchTextField_Previews: PreviewProvider {
    static var previews: some View {
        HidableSearchTextField(
            onSearchToggled: {
                
            },
            destinationProvider: { 
                EmptyView()
                
            },
            isSearchActive: true,
            searchText: .constant("Klk")
        )
    }
}

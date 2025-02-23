// Mapa apresentado no Ciclo de Janeiro 2025
// mapscreen teste na versão alfa em javascript para importar o mapa em javascript
import React, { useState } from 'react';
import { View, Text, StyleSheet, Modal, TouchableOpacity, ScrollView } from 'react-native';
import MapView, { Marker, Callout } from 'react-native-maps';

const MapScreen = () => {
  // Dados das paradas com latitude, longitude e horários
  const stops = [
    { title: 'Zootecnia', lat: -8.020118332550991, long: -34.95433395832667, horarios: ['07:00h','08:05h','09:10h','10:15h','11:00','11:45','12:30','13:15','14:00','15:05','16:10','17:15','18:20','19:25'] },
    { title: 'Cegoe', lat: -8.017635251287492, long: -34.9500308427655, horarios: ['07:03','08:08h','09:13h','10:18h','11:03','11:48','12:33','13:18','13:03','15:08','16:13','17:18','18:23','19:28','20:25h','21:20'] },
    { title: 'Guarina Piscina', lat: -8.015839660962538, long: -34.95072361537375, horarios: ['07:05h','08:10h','09:15h','10:20h','11:15','11:50','10:10','outro horário'] },
    { title: 'Reitoria UFRPE', lat: -8.014544739025151, long: -34.95041164524801, horarios: ['10:45', '11:15', '11:45'] },
    { title: 'Biblioteca', lat: -8.014043108985298, long: -34.948888618982465, horarios: ['10:50', '11:20', '11:50'] },
    { title: 'Veterinária', lat: -8.01507348490432, long: -34.94788882199572, horarios: ['10:55', '11:25', '11:55'] },
    { title: 'Biblioteca Setorial', lat: -8.016096186164424, long: -34.94935051352732, horarios: ['11:00', '11:30', '12:00'] },
    { title: 'Ceagri Computação', lat: -8.017715381494286, long: -34.944797159511495, horarios: ['11:05', '11:35', '12:05'] } //inserir mais horários 
  ];

  const [selectedStop, setSelectedStop] = useState(null); // armazenamento de paradas em modais
  const [modalVisible, setModalVisible] = useState(false); // Controle da visibilidade do modal

  // abrindo modal
  const handleMarkerPress = (stop) => {
    setSelectedStop(stop);  // Seleciona a parada
    setModalVisible(true);   // Exibe o modal
  };

  //função fechando modal
  const closeModal = () => {
    setModalVisible(false);  // Fecha o modal
    setSelectedStop(null);    
  };

  return (
    <View style={styles.container}>
      <MapView
        style={styles.map}
        initialRegion={{
          latitude: -8.020118332550991,
          longitude: -34.95433395832667,
          latitudeDelta: 0.01,
          longitudeDelta: 0.01,
        }}
      >
        {/* adicionar marcadores em mapas */}
        {stops.map((stop, index) => (
          <Marker
            key={index}
            coordinate={{ latitude: stop.lat, longitude: stop.long }}
            title={stop.title}
            onPress={() => handleMarkerPress(stop)} // Ao clicar, abre o modal
          />
        ))}
      </MapView>

      {/*  modal de tabela de horários em primeira versão */}
      {selectedStop && (
        <Modal
          transparent={true}
          animationType="slide"
          visible={modalVisible}
          onRequestClose={closeModal}
        >
          <View style={styles.modalOverlay}>
            <View style={styles.modalContainer}>
              <TouchableOpacity style={styles.closeButton} onPress={closeModal}>
                <Text style={styles.closeButtonText}>X</Text>
              </TouchableOpacity>
              <Text style={styles.modalTitle}>{selectedStop.title}</Text>
              <ScrollView style={styles.scheduleContainer}>
                {selectedStop.horarios.map((horario, index) => (
                  <Text key={index} style={styles.scheduleItem}>
                    {horario}
                  </Text>
                ))}
              </ScrollView>
            </View>
          </View>
        </Modal>
      )}
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  map: {
    flex: 1,
  },
  modalOverlay: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: 'rgba(0, 0, 0, 0.5)', // Fundo escuro para destacar o modal
  },
  modalContainer: {
    backgroundColor: 'white',
    padding: 20,
    borderRadius: 10,
    width: '80%',
    height: '60%',
  },
  closeButton: {
    position: 'absolute',
    top: 10,
    right: 10,
  },
  closeButtonText: {
    fontSize: 20,
    color: 'red',
  },
  modalTitle: {
    fontSize: 20,
    fontWeight: 'bold',
    marginBottom: 20,
    textAlign: 'center',
  },
  scheduleContainer: {
    marginTop: 10,
  },
  scheduleItem: {
    fontSize: 16,
    paddingVertical: 5,
    textAlign: 'center',
  },
});

export default MapScreen;

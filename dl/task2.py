
#print(tf.__version__)
#print(torch.__version__)

#--1

import tensorflow as tf
from tensorflow import keras
from tensorflow.keras import layers

model = keras.Sequential([
layers.Dense(32, activation='relu', input_shape=(10,)),
layers.Dense(1, activation='sigmoid')
])

model.compile(optimizer='adam', loss='binary_crossentropy', metrics=['accuracy'])

model.summary()


#--2

import torch
import torch.nn as nn
import torch.optim as optim

class SimpleModel(nn.Module):
    def __init__(self):
        super(SimpleModel, self).__init__()
        self.fc1 = nn.Linear(10, 32)
        self.fc2 = nn.Linear(32, 1)
    
    def forward(self, x):
        x = torch.relu(self.fc1(x))
        x = torch.sigmoid(self.fc2(x))
        return x

model = SimpleModel()
loss_fn = nn.BCELoss()

optimizer = optim.Adam(model.parameters(), lr=0.01)
print(model)


#--3

import torch
from torch.utils.data import DataLoader, TensorDataset

X = torch.rand(100, 10)
y = torch.randint(0, 2, (100, 1), dtype=torch.float32)

dataset = TensorDataset(X, y)
dataloader = DataLoader(dataset, batch_size=10, shuffle=True)

for epoch in range(4):
    for batch in dataloader:
        inputs, labels = batch
        optimizer.zero_grad()
        outputs = model(inputs)
        loss = loss_fn(outputs, labels)
        loss.backward()
        optimizer.step()
        print(f"Epoch {epoch+1}, Loss: {loss.item()}")

